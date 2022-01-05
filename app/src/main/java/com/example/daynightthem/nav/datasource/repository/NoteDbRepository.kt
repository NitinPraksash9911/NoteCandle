package com.example.daynightthem.nav.datasource.repository

import com.example.daynightthem.nav.datasource.localdb.CalendarDao
import com.example.daynightthem.nav.datasource.localdb.NoteDao
import com.example.daynightthem.nav.datasource.localdb.ReminderDao
import com.example.daynightthem.nav.datasource.localdb.TodoDao
import javax.inject.Inject

class NoteDbRepository @Inject constructor(
    private val noteDao: NoteDao,
    private val calendarDao: CalendarDao,
    private val reminderDao: ReminderDao,
    private val todoDao: TodoDao
) {

    fun getNoteData() = noteDao.getNotes()
    fun getCalendarData() = calendarDao.getCalendar()
    fun getReminderData() = reminderDao.getReminders()
    fun getTodoData() = todoDao.getTodos()




}