package com.noteapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noteapp.repos.NoteRepository
import com.noteapp.models.NotesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
) : ViewModel() {
    val noteLiveData = MutableLiveData<List<NotesEntity>>()


    fun getNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val notes = noteRepository.getAllNotes()
                noteLiveData.postValue(notes)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteNote(note: NotesEntity) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.delete(note)
    }
}