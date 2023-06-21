package com.noteapp.repos

import androidx.room.Database
import androidx.room.RoomDatabase
import com.noteapp.models.NotesEntity

@Database(entities = [NotesEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}