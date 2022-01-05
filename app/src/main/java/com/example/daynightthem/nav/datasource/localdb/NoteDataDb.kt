package com.example.daynightthem.nav.datasource.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.daynightthem.nav.datasource.model.NoteInfo.Calendar
import com.example.daynightthem.nav.datasource.model.NoteInfo.Note
import com.example.daynightthem.nav.datasource.model.NoteInfo.Reminder
import com.example.daynightthem.nav.datasource.model.NoteInfo.TodoData

const val DB_NAME = "note_database"

@Database(entities = [Calendar::class, Note::class, Reminder::class, TodoData::class], version = 1, exportSchema = false)
abstract class NoteDataDb: RoomDatabase() {

    abstract fun getNoteDao(): NoteDao
    abstract fun getCalenderDao(): CalendarDao
    abstract fun getReminderDao(): ReminderDao
    abstract fun getTodoDao(): TodoDao
}