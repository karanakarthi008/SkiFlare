package com.example.skiflaretask.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object RetrofitClient{
    val BASE_URL = "https://crudcrud.com/api/bd7de4a55a8b41249b1fb5a05f89f984/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object ApiClient{
    val apiService : ApiService by lazy {
        RetrofitClient.retrofit.create(ApiService::class.java)
    }
}