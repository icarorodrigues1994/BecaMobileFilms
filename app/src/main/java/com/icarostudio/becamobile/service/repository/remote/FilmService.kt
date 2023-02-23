package com.icarostudio.becamobile.service.repository.remote

import com.icarostudio.becamobile.service.repository.model.FilmModel
import com.icarostudio.becamobile.service.repository.model.ListFilmsModel
import com.icarostudio.becamobile.service.repository.model.ListGenresModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmService {

    @GET("trending/movie/week")
    fun list(@Query("api_key", encoded = true)apiKey: String): Call<ListFilmsModel>


    @GET("movie/{id}")
    fun load(@Path(value = "id", encoded = true)id: Int, @Query("api_key", encoded = true)apiKey: String, @Query("append_to_response")appendResponse: String): Call<FilmModel>


    @GET("genre/movie/list")
    fun getListGenders(@Query("api_key", encoded = true)apiKey: String): Call<ListGenresModel>



}