package com.icarostudio.becamobile.service.listener

interface APIListener<T> {

    fun onSuccess(result: T)
    fun onFailure(menssage: String)

}