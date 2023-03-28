package com.example.noteapp.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.database.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository
    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        getNotes()
    }

    fun getNotes() = repository.allNotes

    fun insertNote(note: Notes)=viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
    fun deleteNote(note: Notes)=viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
}