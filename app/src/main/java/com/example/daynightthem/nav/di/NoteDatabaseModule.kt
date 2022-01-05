package com.example.daynightthem.nav.di

import android.content.Context
import androidx.room.Room
import com.example.daynightthem.nav.datasource.localdb.DB_NAME
import com.example.daynightthem.nav.datasource.localdb.NoteDataDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteDatabaseModule {

    @Provides
    @Singleton
    fun provideNoteDb(@ApplicationContext context: Context): NoteDataDb {

        return Room.databaseBuilder(context, NoteDataDb::class.java, DB_NAME).build()
    }

    @Provides
    @Singleton
    fun providesCalendarDao(db: NoteDataDb) = db.getCalenderDao()

    @Provides
    @Singleton
    fun providesNoteDao(db: NoteDataDb) = db.getNoteDao()

    @Provides
    @Singleton
    fun providesReminderDao(db: NoteDataDb) = db.getReminderDao()

    @Provides
    @Singleton
    fun providesTodoDao(db: NoteDataDb) = db.getTodoDao()

}