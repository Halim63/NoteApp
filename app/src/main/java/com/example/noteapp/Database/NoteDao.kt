package com.example.noteapp.Database

import androidx.lifecycle.LiveData
import androidx.room.*

import com.example.noteapp.Models.Notes

@Dao
abstract class NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(note: Notes)

    @Delete
    abstract suspend fun delete(note: Notes)

    @Query("SELECT * FROM notes_table ORDER BY id DESC")
    abstract fun getAllNotes(): LiveData<List<Notes>>
}