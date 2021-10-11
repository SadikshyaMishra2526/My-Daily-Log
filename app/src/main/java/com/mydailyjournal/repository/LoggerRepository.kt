package com.mydailyjournal.repository

import androidx.lifecycle.LiveData
import com.mydailyjournal.database.DailyLogger
import com.mydailyjournal.database.LoggerDao

class LoggerRepository(private val loggerDao:LoggerDao) {
    val getLoggerDetails: LiveData<List<DailyLogger>> = loggerDao.getAllDetailsOfLogger()

   suspend fun addLoggerDetails(dailyLogger:DailyLogger){
        loggerDao.addLoggerDetails(dailyLogger)
    }
}