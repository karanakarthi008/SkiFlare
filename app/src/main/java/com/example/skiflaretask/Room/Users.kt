package com.example.skiflaretask.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "email") var email:String,
    @ColumnInfo(name = "mobile") var mobile: String,
    @ColumnInfo(name = "gender") var gender:String
)