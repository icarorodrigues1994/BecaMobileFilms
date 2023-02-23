package com.icarostudio.becamobile.service.repository

import android.content.Context
import com.icarostudio.becamobile.R
import com.icarostudio.becamobile.service.constants.FilmsConstants
import com.icarostudio.becamobile.service.listener.APIListener
import com.icarostudio.becamobile.service.repository.local.FilmDatabase
import com.icarostudio.becamobile.service.repository.model.*
import com.icarostudio.becamobile.service.repository.remote.FavoritesService
import com.icarostudio.becamobile.service.repository.remote.FilmService
import com.icarostudio.becamobile.service.repository.remote.RetrofitClient
import com.icarostudio.becamobile.service.repository.remote.TvService

class FilmRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(FilmService::class.java)
    private val remoteTV = RetrofitClient.getService(TvService::class.java)
    private val remoteFavorite = RetrofitClient.getService(FavoritesService::class.java)

    private val database = FilmDatabase.getDatabase(context).priorityDAO()

    companion object {
        private val listOfMonths = mutableMapOf<String, String>(
            Pair("01", "January"),
            Pair("02", "February"),
            Pair("03", "March"),
            Pair("04", "April"),
            Pair("05", "May"),
            Pair("06", "June"),
            Pair("07", "July"),
            Pair("08", "August"),
            Pair("09", "September"),
            Pair("10", "October"),
            Pair("11", "November"),
            Pair("12", "December")
        )

        fun getMonth(month: String): String {
            return listOfMonths[month] ?: ""  // OPERADOR ELVIS
        }

        private val cache = mutableMapOf<Int, String>()

        fun getDescription(id: Int): String {
            return cache[id] ?: ""  // OPERADOR ELVIS
        }

        fun setDescription(id: Int, str: String) {
            cache[id] = str
        }

    }



    // LISTA DE FILMES
    fun list(listener: APIListener<ListFilmsModel>){
        if(!isConnectionAvailable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }

        val call = remote.list(FilmsConstants.QUERY.API_KEY)
        val s = ""
        executeCall(call,listener)
    }

    // LOAD DE FILME
    fun load(id: Int, listener: APIListener<FilmModel>){
        if(!isConnectionAvailable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = remote.load(id,FilmsConstants.QUERY.API_KEY,FilmsConstants.QUERY.VIDEOS)
        executeCall(call,listener)
    }

    // LISTA DE GENEROS
    fun getListGenders(listener: APIListener<ListGenresModel>){
        if(!isConnectionAvailable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }

        val call = remote.getListGenders(FilmsConstants.QUERY.API_KEY)
        executeCall(call,listener)

    }

    fun tvList(listener: APIListener<ListTvModel>){
        if(!isConnectionAvailable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = remoteTV.list(FilmsConstants.QUERY.API_KEY)
        executeCall(call,listener)
    }

    fun tvLoad(id: Int, listener: APIListener<TvModel>){
        if(!isConnectionAvailable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = remoteTV.load(id,FilmsConstants.QUERY.API_KEY,FilmsConstants.QUERY.VIDEOS)
        executeCall(call,listener)
    }

    fun getListGendersTv(listener: APIListener<ListGenresModel>){
        if(!isConnectionAvailable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }

        val call = remoteTV.getListGenders(FilmsConstants.QUERY.API_KEY)
        executeCall(call,listener)

    }

    fun isFavorite (listener: APIListener<ListFilmsModel>){
        if(!isConnectionAvailable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }

        val call = remoteFavorite.listFavoriteMovies(FilmsConstants.QUERY.API_KEY,FilmsConstants.QUERY.SESSIO_ID)
        executeCall(call,listener)
    }

    fun addOrRemoveFavorite (mediaType: String, media_id: Int, favorite: Boolean, listener: APIListener<FavoriteModel>){
        if(!isConnectionAvailable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }

        val call = remoteFavorite.addFavorite(favorite,mediaType,media_id,FilmsConstants.QUERY.API_KEY,FilmsConstants.QUERY.SESSIO_ID)
        val s = ""
        executeCall(call,listener)
    }






    // LOCAL
    fun save(list: ListGenresModel){
        database.clear()
        database.save(list.genres)
    }

    fun saveTv(list: ListGenresModel){
        database.save(list.genres)
    }

    // LOCAL
    fun get(): List<GenreModel>{
       return database.list()
    }

    //Requisição no banco local ou no cache
    fun getDescription(id: Int): String{
        val cached = FilmRepository.getDescription(id)

        return if (cached == ""){
            val description = database.getDescription(id)
            setDescription(id, description)
            description
        } else {
            cached
        }
    }

}