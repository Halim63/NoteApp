package com.example.noteapp.Database

import androidx.lifecycle.LiveData
import com.example.noteapp.Models.Notes

class NoteRepository(private val noteDao:NoteDao) {
    val allNotes = noteDao.getAllNotes()

    suspend fun insert(note: Notes){
        noteDao.insert(note)
    }
    suspend fun delete(note: Notes){
        noteDao.delete(note)
    }
}