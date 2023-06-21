package com.noteapp.repos

import com.noteapp.models.NotesEntity
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao,
) {
    fun getAllNotes() = noteDao.getAllNotes()

    suspend fun insert(note: NotesEntity) {
        noteDao.insert(note)
    }

    suspend fun delete(note: NotesEntity) {
        noteDao.delete(note)
    }
}