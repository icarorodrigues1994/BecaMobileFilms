package com.icarostudio.becamobile.service.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.google.gson.Gson
import com.icarostudio.becamobile.R
import com.icarostudio.becamobile.service.constants.FilmsConstants
import com.icarostudio.becamobile.service.listener.APIListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository (val context: Context){

    fun <T> executeCall (call: Call<T>, listener: APIListener<T>){
        val s = ""
        call.enqueue(object: Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if(response.code() == FilmsConstants.HTTP.SUCCESS || response.code() == FilmsConstants.HTTP.SUCCESS_CREATED){
                    response.body()?.let { listener.onSuccess(it) }
                var s = ""// Consigo pegar o que vem da api
                } else {
                    listener.onFailure(failResponse(response.errorBody()!!.toString()))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val s = t.message
                val d = t.cause
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

    /* ESSA FUNÇÃO É PARA CONVERTER O JSON EM STRING...
       * -- o codigo da resposta quando não é 200, o retorno é um JSON de string,
       * -- então é necessário converter esse Json em string e a função abaixo vai
       * -- fazer isso para nois
       * */
    private fun failResponse(str: String): String {
        return Gson().fromJson(str, String::class.java)
    }


    // Função que verifica se tem conectividade com a internet
    fun isConnectionAvailable(): Boolean{
        var result = false

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNet = cm.activeNetwork ?: return false
        val networkCapabilities = cm.getNetworkCapabilities(activeNet) ?: return false

        result = when{
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            else -> false
        }

        return result
    }

}