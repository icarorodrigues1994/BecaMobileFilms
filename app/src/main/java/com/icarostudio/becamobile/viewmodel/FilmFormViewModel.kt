package com.icarostudio.becamobile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.icarostudio.becamobile.service.constants.FilmsConstants
import com.icarostudio.becamobile.service.listener.APIListener
import com.icarostudio.becamobile.service.repository.FilmRepository
import com.icarostudio.becamobile.service.repository.model.FavoriteModel
import com.icarostudio.becamobile.service.repository.model.FilmModel
import com.icarostudio.becamobile.service.repository.model.ListFilmsModel
import com.icarostudio.becamobile.service.repository.securityPrefe.SecurityPreferences

class FilmFormViewModel(application: Application) : AndroidViewModel(application) {

    private val filmRepository = FilmRepository(application.applicationContext)
    private val securityPreferences = SecurityPreferences(application.applicationContext)


    private val _film = MutableLiveData<FilmModel>()
    val film: LiveData<FilmModel> = _film

    private val _like = MutableLiveData<Boolean>()
    val like: LiveData<Boolean> = _like

    private val _favorite = MutableLiveData<FavoriteModel>()
    val favorite: LiveData<FavoriteModel> = _favorite

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite


    fun load(id: Int){
        filmRepository.load(id, object : APIListener<FilmModel> {
            override fun onSuccess(result: FilmModel) {
                _film.value = result
            }

            override fun onFailure(menssage: String) {

            }

        })
    }

    fun isFavorite(id: Int){
        filmRepository.isFavorite(object : APIListener<ListFilmsModel> {
            override fun onSuccess(result: ListFilmsModel) {
                _isFavorite.value = roamListFilms(result, id)
            }

            override fun onFailure(menssage: String) {

            }

        })
    }

    fun addOrRemoveFavorite(id: Int, isFavorite: Boolean){
        filmRepository.addOrRemoveFavorite(FilmsConstants.TYPE.MOVIE, id, false, object : APIListener<FavoriteModel> {
            override fun onSuccess(result: FavoriteModel) {
                _favorite.value = result
                val s = ""
            }

            override fun onFailure(menssage: String) {

            }

        })
    }

    fun handleLike(id: Int){
        val src = securityPreferences.get(id.toString())
        if(src == ""){
            securityPreferences.store(id.toString(),id.toString())
            _like.value = true
        } else {
            securityPreferences.remove(id.toString())
            _like.value = false
        }
    }

    fun verifyLike(id: Int): Boolean{
        val src = securityPreferences.get(id.toString())
        return src != ""
    }



    private fun roamListFilms(list: ListFilmsModel, id: Int) : Boolean{
        return list.results.find {it.id == id} != null
    }

}