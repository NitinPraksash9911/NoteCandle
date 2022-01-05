package com.example.daynightthem.nav.datasource.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class NoteInfo {
    abstract val id: Long

    @Entity
    data class Calendar(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val calId: Long,
        val subject: String = "",
        val body: String = "",
        var isImportant: Boolean = false,
        var isStarred: Boolean = false,
    ) : NoteInfo() {
        override val id
        get() = calId
    }

    @Entity
    data class Note(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val noteId: Long,
        val subject: String = "",
        val body: String = "",
        var isImportant: Boolean = false,
        var isStarred: Boolean = false,
    ) : NoteInfo() {
        override val id
        get() = noteId
    }

    @Entity
    data class TodoData(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val todoId: Long,
        val subject: String = "",
        val body: String = "",
        var isImportant: Boolean = false,
        var isStarred: Boolean = false,
    ) : NoteInfo() {
        override val id
        get() = todoId
    }

    @Entity
    data class Reminder(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val remId: Long,
        val subject: String = "",
        val body: String = "",
        var isImportant: Boolean = false,
        var isStarred: Boolean = false,
    ) : NoteInfo() {
        override val id
        get() = remId
    }
}

object NoteInfoDiffCallback : DiffUtil.ItemCallback<NoteInfo>() {

    override fun areItemsTheSame(oldItem: NoteInfo, newItem: NoteInfo): Boolean {

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteInfo, newItem: NoteInfo): Boolean {
        return oldItem.equals(newItem)
    }

}


