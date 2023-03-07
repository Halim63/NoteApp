package com.example.noteapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.Models.Notes
import com.example.noteapp.R

class NotesAdapter(private val context: Context)
    : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    val allNotes=ArrayList<Notes>()
     class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val tvTitle=itemView.findViewById<TextView>(R.id.tvTitle)
        val tvNote=itemView.findViewById<TextView>(R.id.tvNote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder= NoteViewHolder(
            LayoutInflater.from(parent.context ).inflate(R.layout.list_item,parent,false)

        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote=allNotes[position]
        holder.tvTitle.text=currentNote.title
        holder.tvNote.text=currentNote.note
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateData(newData: List<Notes>?) {
        allNotes.clear()
        allNotes.addAll(newData!!)
        notifyDataSetChanged()
    }

    fun getNoteAT(position: Int):Notes{
        return allNotes[position]
    }




}