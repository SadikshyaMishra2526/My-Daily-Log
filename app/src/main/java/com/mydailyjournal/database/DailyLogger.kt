package com.mydailyjournal.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logger_details")
data class DailyLogger(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var fullName:String,
    var email:String,
    var password:String,
    var age:Int,
    var joinedDate:String
)