package com.sportdata.dazn.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private val client = OkHttpClient.Builder().build()

    //dodanie konfiguracji retrofit wraz z base url
    private val retrofit=Retrofit.Builder()
            .baseUrl("https://us-central1-dazn-sandbox.cloudfunctions.net")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()


    fun<T> buildApiService(service:Class<T>): T{

        return retrofit.create(service)
    }
}