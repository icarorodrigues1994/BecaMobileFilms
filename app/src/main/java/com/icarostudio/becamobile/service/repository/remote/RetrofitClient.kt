package com.icarostudio.becamobile.service.repository.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor(){

    companion object {

        private lateinit var INSTANCE : Retrofit


        private fun getRetrofitInstance(): Retrofit{
            val httpClient = OkHttpClient.Builder()

            //INTERCEPTAÇÃO DA REQUISIÇÃO
            httpClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build()
                    return chain.proceed(request)
                }
            })

            if (!Companion::INSTANCE.isInitialized){
                //INSTÂNCIA DO RETROFIT
                synchronized(RetrofitClient::class){
                    INSTANCE = Retrofit.Builder()
                        .baseUrl("https://api.themoviedb.org/3/")
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }

            }
            return INSTANCE
        }


        fun <T> getService(serviceClass: Class<T>) : T {
            return getRetrofitInstance().create(serviceClass)
        }


    }
}