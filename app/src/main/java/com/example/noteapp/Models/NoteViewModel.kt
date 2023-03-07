package com.example.noteapp.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.noteapp.Database.NoteDatabase
import com.example.noteapp.Database.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository
    val allNotes: LiveData<List<Notes>>
    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun insertNote(note: Notes)=viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
    fun deleteNote(note: Notes)=viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
}