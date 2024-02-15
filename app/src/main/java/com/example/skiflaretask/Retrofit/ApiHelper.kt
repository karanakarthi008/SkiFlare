package com.example.skiflaretask.Retrofit

import com.example.skiflaretask.data.Users
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ApiHelper {

    fun getUsers(): Flow<List<Users>>
}

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override fun getUsers() = flow { emit(apiService.getUsers()) }

}