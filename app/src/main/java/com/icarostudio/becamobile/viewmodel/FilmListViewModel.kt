package com.icarostudio.becamobile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icarostudio.becamobile.service.listener.APIListener
import com.icarostudio.becamobile.service.repository.FilmRepository
import com.icarostudio.becamobile.service.repository.model.FilmModel
import com.icarostudio.becamobile.service.repository.model.ListFilmsModel

class FilmListViewModel(application: Application) : AndroidViewModel(application) {

    private val filmRepository = FilmRepository(application.applicationContext)

    private val _films= MutableLiveData<ListFilmsModel>()
    val films: LiveData<ListFilmsModel> = _films


    fun list(){
        filmRepository.list(object : APIListener<ListFilmsModel>{
            override fun onSuccess(result: ListFilmsModel) {
                val s = ""
                result.results.forEach(){
                    it.genreDescription = filmRepository.getDescription(it.genresIds.first())
                    it.releaseDateFormat = convertDate(it.releaseDate)
                    val s = ""
                }
                _films.value = result
            }

            override fun onFailure(menssage: String) {
                val s = menssage
            }

        })
    }

    private fun convertDate(releaseDate: String): String {
        val day = releaseDate.substring(8,10)
        val month = releaseDate.substring(5,7)
        val year = releaseDate.substring(0,4)

        val mes = FilmRepository.getMonth(month)
        return "$mes $day, $year"
    }
}