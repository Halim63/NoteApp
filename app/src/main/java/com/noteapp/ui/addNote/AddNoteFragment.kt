package com.noteapp.ui.addNote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.noteapp.models.NotesEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_note.et_note
import kotlinx.android.synthetic.main.fragment_add_note.et_title
import kotlinx.android.synthetic.main.fragment_add_note.fb_Save_note

@AndroidEntryPoint

class AddNoteFragment : Fragment() {

    private val addNoteViewModel by viewModels<AddNoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {
        fb_Save_note.setOnClickListener {
            onSaveBtnClicked()
        }
    }

    private fun onSaveBtnClicked() {
        val title = et_title.text.toString()
        val notesContent = et_note.text.toString()
        isNoteViewEmpty(title, notesContent)
        addNoteViewModel.insertNote(NotesEntity(title, notesContent))


    }

    private fun isNoteViewEmpty(title: String, notesContent: String) {
        if (title.isNotEmpty() || notesContent.isNotEmpty()) {
//            addNoteViewModel.saveNoteInDbLiveData.observe(viewLifecycleOwner){ isNoteSaved ->
//                if (isNoteSaved) {
//                    findNavController().navigateUp()
//
//                } else {
//                    Toast.makeText(requireContext(), R.string.can_not_save_image, Toast.LENGTH_LONG)
//                        .show()
//                }
//            }
            setUpSaveNoteNavigation()


        }
    }

    private fun setUpSaveNoteNavigation() {
        val action = AddNoteFragmentDirections.actionAddNoteFragmentToHomeFragment()
        findNavController().navigate(action)
    }


}
