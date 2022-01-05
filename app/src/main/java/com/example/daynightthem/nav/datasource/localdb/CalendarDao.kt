package com.example.daynightthem.nav.datasource.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.daynightthem.nav.datasource.model.NoteInfo.Calendar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.selects.select

@Dao
interface CalendarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCalender(vararg calendar: Calendar)

    @Query("select * from calendar")
     fun getCalendar():Flow<List<Calendar>>
}