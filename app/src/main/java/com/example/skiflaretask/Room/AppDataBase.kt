package com.example.skiflaretask.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Users::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getUserDao() : UserDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDataBase(context : Context) : AppDatabase {
            return INSTANCE ?: synchronized(this){
                val instance  = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}