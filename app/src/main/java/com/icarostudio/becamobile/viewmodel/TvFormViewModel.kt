package com.icarostudio.becamobile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.icarostudio.becamobile.service.listener.APIListener
import com.icarostudio.becamobile.service.repository.FilmRepository
import com.icarostudio.becamobile.service.repository.model.FilmModel
import com.icarostudio.becamobile.service.repository.model.ResultsVideos
import com.icarostudio.becamobile.service.repository.model.TvModel
import com.icarostudio.becamobile.service.repository.securityPrefe.SecurityPreferences

class TvFormViewModel (application: Application) : AndroidViewModel(application) {

    private val filmRepository = FilmRepository(application.applicationContext)
    private val securityPreferences = SecurityPreferences(application.applicationContext)


    private val _tv= MutableLiveData<TvModel>()
    val tv: LiveData<TvModel> = _tv

    private val _like = MutableLiveData<Boolean>()
    val like: LiveData<Boolean> = _like


    fun load(id: Int) {
        filmRepository.tvLoad(id, object : APIListener<TvModel> {
            override fun onSuccess(result: TvModel) {
                _tv.value = result
            }

            override fun onFailure(menssage: String) {

            }

        })
    }

    fun handleLike(id: Int) {
        val src = securityPreferences.get(id.toString())
        if (src == "") {
            securityPreferences.store(id.toString(), id.toString())
            _like.value = true
        } else {
            securityPreferences.remove(id.toString())
            _like.value = false
        }
    }

    fun verifyLike(id: Int): Boolean {
        val src = securityPreferences.get(id.toString())
        return src != ""
    }
}
