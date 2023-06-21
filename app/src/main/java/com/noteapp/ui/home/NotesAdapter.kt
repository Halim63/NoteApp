package com.noteapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.noteapp.models.NotesEntity
import com.example.noteapp.R
import kotlin.random.Random

class NotesAdapter
    : ListAdapter<NotesEntity, NotesAdapter.NoteViewHolder>(UserDiffUtil()) {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val notesLayout: CardView? = itemView.findViewById(R.id.card)
        val tvTitle: TextView? = itemView.findViewById(R.id.tvTitle)
        val tvNote: TextView? = itemView.findViewById(R.id.tvNote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = currentList[position]
        holder.tvTitle?.text = currentNote.title
        holder.tvNote?.text = currentNote.note
        holder.notesLayout?.setCardBackgroundColor(
            holder.itemView.resources.getColor(
                randomColor(),
                null
            )
        )
    }


    fun randomColor(): Int {
        val list = ArrayList<Int>()
        list.add(R.color.noteColor1)
        list.add(R.color.noteColor2)
        list.add(R.color.noteColor3)
        list.add(R.color.noteColor4)
        list.add(R.color.noteColor5)
        list.add(R.color.noteColor6)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]
    }
}

class UserDiffUtil : DiffUtil.ItemCallback<NotesEntity>() {
    override fun areItemsTheSame(oldItem: NotesEntity, newItem: NotesEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NotesEntity, newItem: NotesEntity): Boolean {
        return oldItem == newItem
    }

}
