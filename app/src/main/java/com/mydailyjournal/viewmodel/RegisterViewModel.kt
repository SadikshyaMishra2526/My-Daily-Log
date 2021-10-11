package com.mydailyjournal.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.mydailyjournal.database.DailyLogger
import com.mydailyjournal.database.LoggerDatabase
import com.mydailyjournal.repository.LoggerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

 class RegisterViewModel(application: Application): AndroidViewModel(application) {

    public val fetchRegisterLogger : LiveData<List<DailyLogger>>
    private val repository : LoggerRepository
    init {
        val loggerDao= LoggerDatabase.getLoggerDatabase(application).loggerDao()
        repository = LoggerRepository(loggerDao)
        fetchRegisterLogger=repository.getLoggerDetails
    }
    fun addDailyLogger(logger: DailyLogger){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLoggerDetails(logger)
        }
    }
}