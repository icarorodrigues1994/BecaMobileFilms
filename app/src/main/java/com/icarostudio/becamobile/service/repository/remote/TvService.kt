package com.icarostudio.becamobile.service.repository.remote

import com.icarostudio.becamobile.service.repository.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvService {

    @GET("trending/tv/week")
    fun list(@Query("api_key", encoded = true)apiKey: String): Call<ListTvModel>


    @GET("tv/{id}")
    fun load(@Path(value = "id", encoded = true)id: Int, @Query("api_key", encoded = true)apiKey: String, @Query("append_to_response")appendResponse: String): Call<TvModel>


    @GET("genre/tv/list")
    fun getListGenders(@Query("api_key", encoded = true)apiKey: String): Call<ListGenresModel>

}