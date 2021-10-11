package com.mydailyjournal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mydailyjournal.database.DailyLogger
import com.mydailyjournal.database.LogDetails
import com.mydailyjournal.database.LoggerDatabase
import com.mydailyjournal.repository.LogRepository
import com.mydailyjournal.repository.LoggerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogViewModel(application: Application):AndroidViewModel(application) {

    val fetchAllData : LiveData<List<LogDetails>>
    private val repository : LogRepository
    init {
        val loggerDao=LoggerDatabase.getLoggerDatabase(application).loggerDao()
        repository = LogRepository(loggerDao)
        fetchAllData=repository.getLogDetails
    }

    fun addDailyLogger(logger: LogDetails){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLogDetails(logger)
        }
    }
}