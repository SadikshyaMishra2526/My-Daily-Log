package com.mydailyjournal.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoggerDao {
//   for storing and fetching users details
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLoggerDetails(logger:DailyLogger)

    @Query("SELECT * FROM logger_details ORDER BY id ASC")
    fun getAllDetailsOfLogger():LiveData<List<DailyLogger>>


//   for storing and fetching users contains
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLogDetails(logger:LogDetails)

    @Query("SELECT * FROM log_details ORDER BY id ASC")
    fun getAllDetailsOfLog():LiveData<List<LogDetails>>
}