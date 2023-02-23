package com.icarostudio.becamobile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.icarostudio.becamobile.service.listener.APIListener
import com.icarostudio.becamobile.service.repository.FilmRepository
import com.icarostudio.becamobile.service.repository.model.ListGenresModel

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val filmRepository = FilmRepository(application.applicationContext)

    private val _genres= MutableLiveData<ListGenresModel>()
    val genres: LiveData<ListGenresModel> = _genres


    fun getListGenders(){
        filmRepository.getListGenders(object : APIListener<ListGenresModel>{
            override fun onSuccess(result: ListGenresModel) {
                filmRepository.save(result)
                getListGendersTv()
            }

            override fun onFailure(menssage: String) {

            }

        })
    }

    private fun getListGendersTv(){
        filmRepository.getListGendersTv(object : APIListener<ListGenresModel>{
            override fun onSuccess(result: ListGenresModel) {
                filmRepository.saveTv(result)
            }

            override fun onFailure(menssage: String) {

            }

        })
    }

}