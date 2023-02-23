package com.icarostudio.becamobile.service.repository.remote

import com.icarostudio.becamobile.service.repository.model.FavoriteModel
import com.icarostudio.becamobile.service.repository.model.ListFilmsModel
import retrofit2.Call
import retrofit2.http.*

interface FavoritesService {

    @GET("account/17142537/favorite/movies")
    fun listFavoriteMovies(@Query("api_key", encoded = true)apiKey: String,
                           @Query("session_id", encoded = true)sessionId: String): Call<ListFilmsModel>


    @GET("account/17142537/favorite/tv")
    fun listFavoriteTvs(@Query("api_key", encoded = true)apiKey: String,
                        @Query("session_id", encoded = true)sessionId: String): Call<ListFilmsModel>



    @POST("account/17142537/favorite")
    @FormUrlEncoded //Enviando um corpo na requisição, no caso no Body
    fun addFavorite(@Field("favorite") favorite: Boolean,
                    @Field("media_type") mediaType: String,
                    @Field("media_id") media_id: Int,
                    @Query("api_key", encoded = true)apiKey: String,
                    @Query("session_id", encoded = true)sessionId: String): Call<FavoriteModel>
}