package com.example.myapp014amynotehub

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp014amynotehub.databinding.ItemNoteBinding

class NoteAdapter(
    private val notes: List<Note>,
    private val onDeleteClick: (Note) -> Unit,  // Funkce pro mazání poznámky
    private val onEditClick: (Note) -> Unit    // Funkce pro editaci poznámky
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
    }

    inner class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            binding.noteTitle.text = note.title
            binding.noteContentPreview.text = note.content

            // Kliknutí na ikonu pro mazání
            binding.iconDelete.setOnClickListener {
                AlertDialog.Builder(itemView.context)
                    .setTitle("Smazat poznámku")
                    .setMessage("Opravdu chcete tuto poznámku smazat?")
                    .setPositiveButton("Ano") { _, _ ->
                        onDeleteClick(note)  // Vyvolání funkce pro mazání poznámky
                    }
                    .setNegativeButton("Ne", null)
                    .show()
            }

            // Kliknutí na ikonu pro editaci
            binding.iconEdit.setOnClickListener {
                onEditClick(note)  // Vyvolání funkce pro editaci poznámky
            }


        }
    }
}