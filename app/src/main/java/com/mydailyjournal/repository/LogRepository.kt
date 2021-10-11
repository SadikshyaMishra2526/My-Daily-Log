package com.mydailyjournal.repository

import androidx.lifecycle.LiveData
import com.mydailyjournal.database.DailyLogger
import com.mydailyjournal.database.LogDetails
import com.mydailyjournal.database.LoggerDao

class LogRepository(private val loggerDao: LoggerDao) {

    val getLogDetails: LiveData<List<LogDetails>> = loggerDao.getAllDetailsOfLog()

    suspend fun addLogDetails(logDetails: LogDetails){
        loggerDao.addLogDetails(logDetails)
    }
}