package com.example.daynightthem.nav.datasource.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.daynightthem.nav.datasource.model.NoteInfo.TodoData
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(vararg todoData: TodoData)

    @Query("select * from calendar")
     fun getTodos(): Flow<List<TodoData>>
}