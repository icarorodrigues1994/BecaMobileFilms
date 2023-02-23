package com.icarostudio.becamobile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.icarostudio.becamobile.service.listener.APIListener
import com.icarostudio.becamobile.service.repository.FilmRepository
import com.icarostudio.becamobile.service.repository.model.ListFilmsModel
import com.icarostudio.becamobile.service.repository.model.ListGenresModel
import com.icarostudio.becamobile.service.repository.model.ListTvModel

class TvListViewModel(application: Application) : AndroidViewModel(application) {

    private val filmRepository = FilmRepository(application.applicationContext)

    private val _tv= MutableLiveData<ListTvModel>()
    val tv: LiveData<ListTvModel> = _tv

    fun list(){
        filmRepository.tvList(object : APIListener<ListTvModel> {
            override fun onSuccess(result: ListTvModel) {
                result.results.forEach(){
                    it.genreDescription = filmRepository.getDescription(it.genresIds.first())
                    it.releaseDateFormat = convertDate(it.firstAirDate)
                }
                _tv.value = result
            }

            override fun onFailure(menssage: String) {
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

