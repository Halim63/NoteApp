package com.example.noteapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.Models.Notes
import com.example.noteapp.R
import kotlin.random.Random

class NotesAdapter(private val context: Context)
    : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val allNotes=ArrayList<Notes>()
     class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
         val notesLayout= itemView.findViewById<CardView>(R.id.card)
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
        holder.notesLayout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(),null))
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
    fun randomColor():Int{
        val list=ArrayList<Int>()
        list.add(R.color.noteColor1)
        list.add(R.color.noteColor2)
        list.add(R.color.noteColor3)
        list.add(R.color.noteColor4)
        list.add(R.color.noteColor5)
        list.add(R.color.noteColor6)

        val seed=System.currentTimeMillis().toInt()
        val randomIndex= Random(seed).nextInt(list.size)
        return list[randomIndex]
    }




}