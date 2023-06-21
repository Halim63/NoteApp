package com.noteapp.ui.addNote

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
class AddNoteViewModel @Inject constructor(
    private val repository: NoteRepository,
) : ViewModel() {
    val saveNoteInDbLiveData = MutableLiveData<Boolean>()


    fun insertNote(note: NotesEntity) = viewModelScope.launch(Dispatchers.IO) {

        try {
            repository.insert(note)
            saveNoteInDbLiveData.postValue(true)
        } catch (e: Exception) {
            e.printStackTrace()
            saveNoteInDbLiveData.postValue(false)

        }
//        repository.insert(note)
    }
}