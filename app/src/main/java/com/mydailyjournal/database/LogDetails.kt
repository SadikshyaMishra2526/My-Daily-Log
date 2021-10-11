package com.mydailyjournal.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "log_details")
data class LogDetails(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var log_title:String,
    var log_added_date:String,
    var log_description:String,
    var log_image:String,
    var log_added_at:String,
    var log_added_by:String
)