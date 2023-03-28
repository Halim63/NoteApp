package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.models.NoteViewModel
import com.example.noteapp.models.Notes
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNote : AppCompatActivity() {

    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
//        setSupportActionBar(myToolbar)
        viewModel= ViewModelProvider(this)[NoteViewModel::class.java]
        tv_save.setOnClickListener {
           saveNote()
        }
    }
    private fun saveNote() {
        val title= et_title.text.toString()
        val notesContent=et_note.text.toString()
        if (title.isNotEmpty() || notesContent.isNotEmpty()){
            viewModel.insertNote(Notes(title,notesContent))

            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater= menuInflater
        inflater.inflate(R.menu.option_save,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemView=item.itemId
        return when(itemView){
            R.id.save -> {
                val title= et_title.text.toString()
                val notesContent=et_note.text.toString()
                if (title.isNotEmpty() || notesContent.isNotEmpty()){
                    viewModel.insertNote(Notes(title,notesContent))

                    val intent= Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

}