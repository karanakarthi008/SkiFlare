package com.example.skiflaretask

import android.app.Application
import com.example.skiflaretask.Room.AppDatabase

class UserApplication: Application() {
    val database : AppDatabase by lazy {
        AppDatabase.getDataBase(this)
    }
}