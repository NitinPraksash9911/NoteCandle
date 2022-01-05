package com.example.daynightthem.nav.datasource.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.daynightthem.nav.datasource.model.NoteInfo.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(vararg reminder: Reminder)

    @Query("select * from calendar")
     fun getReminders(): Flow<List<Reminder>>
}