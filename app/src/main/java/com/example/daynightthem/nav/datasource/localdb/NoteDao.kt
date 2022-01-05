package com.example.daynightthem.nav.datasource.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.daynightthem.nav.datasource.model.NoteInfo.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(vararg note: Note)

    @Query("select * from note")
     fun getNotes(): Flow<List<Note>>
}