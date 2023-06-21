package com.noteapp.repos

import androidx.room.*

import com.noteapp.models.NotesEntity

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: NotesEntity)

    @Delete
    suspend fun delete(note: NotesEntity)

    @Query("SELECT * FROM notes_table ORDER BY id DESC")
    fun getAllNotes(): List<NotesEntity>
}