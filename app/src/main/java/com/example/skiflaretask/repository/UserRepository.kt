package com.example.skiflaretask.repository

import com.example.skiflaretask.Retrofit.ApiClient
import com.example.skiflaretask.Retrofit.ApiHelper
import com.example.skiflaretask.Room.AppDatabase
import com.example.skiflaretask.Room.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext


class UserRepository(private val apiHelper: ApiHelper, val database: AppDatabase) {

//    val userList: Flow<List<com.example.skiflaretask.data.Users>>
//        get() = getUsers()

     suspend fun getUsers() : Flow<List<com.example.skiflaretask.data.Users>> {
        val userList : Flow<List<com.example.skiflaretask.data.Users>>
        withContext(Dispatchers.IO) {
            userList = apiHelper.getUsers()
            val userListRoom = userList.toList()
            for (list in userListRoom) {
                for (user in list){
                    val temUser= Users(name = user.name, email = user.email, mobile = user.mobile, gender = user.gender)
                    database.getUserDao().insertAll(temUser)
                }
            }
        }
        return userList
    }
}