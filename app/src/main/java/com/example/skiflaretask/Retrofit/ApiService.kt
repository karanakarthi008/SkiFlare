package com.example.skiflaretask.Retrofit

import com.example.skiflaretask.data.Users
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<Users>
}