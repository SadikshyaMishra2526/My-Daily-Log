package com.mydailyjournal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database( entities = [DailyLogger::class,LogDetails::class],version = 3,exportSchema = false)

abstract class LoggerDatabase :RoomDatabase() {

    abstract fun loggerDao():LoggerDao
    companion object{
        @Volatile
        private var INSTANCE :LoggerDatabase?=null
        fun getLoggerDatabase(context: Context):LoggerDatabase{
        var tempInstance= INSTANCE
        if(tempInstance!=null){
            return tempInstance
        }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    LoggerDatabase::class.java,
                    "logger_database"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}