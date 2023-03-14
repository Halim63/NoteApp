package com.example.noteapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.work.*
import com.example.noteapp.Adapter.NotesAdapter
import com.example.noteapp.Models.NoteViewModel
import com.example.noteapp.Models.Notes
import com.example.noteapp.Worker.ReminderWorker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NoteViewModel
    lateinit var notesAdapter: NotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        periodicRequest()
        recycler_view.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        val adaptor = NotesAdapter(this)
        recycler_view.adapter = adaptor
        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        viewModel.allNotes.observe(this) {
            adaptor.updateData(it)
            updateUI(it)
        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteNote(adaptor.getNoteAT(viewHolder.adapterPosition))
            }

        }).attachToRecyclerView(recycler_view)


        fb_add_note.setOnClickListener {
            val intent = Intent(this, AddNote::class.java)

            startActivity(intent)
        }

    }

    private fun updateUI(note: List<Notes>) {
        if (note.isNotEmpty()) {
            recycler_view.visibility = View.VISIBLE
            tv_no_notes_available.visibility = View.GONE
        } else {
            recycler_view.visibility = View.GONE
            tv_no_notes_available.visibility = View.VISIBLE
        }
    }

    fun periodicRequest() {
        val myWorkRequest =
            PeriodicWorkRequest.Builder(
                ReminderWorker::class.java,
                60,
                TimeUnit.MINUTES)
            .addTag("my_id")
            .build()
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("my_id", ExistingPeriodicWorkPolicy.KEEP, myWorkRequest)

    }


}
