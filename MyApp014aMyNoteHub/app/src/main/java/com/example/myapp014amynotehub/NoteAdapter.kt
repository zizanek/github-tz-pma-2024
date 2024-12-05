package com.example.myapp014amynotehub

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp014amynotehub.databinding.ItemNoteBinding
import kotlinx.coroutines.launch

class NoteAdapter(
    private val notes: List<Note>,
    private val onDeleteClick: (Note) -> Unit,  // Funkce pro mazání poznámky
    private val onEditClick: (Note) -> Unit,    // Funkce pro editaci poznámky
    private val lifecycleScope: LifecycleCoroutineScope,  // Přidán lifecycleScope
    private val database: NoteHubDatabase  // Přidána instance databáze
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

            // Ověření, zda categoryId není null
            val categoryId = note.categoryId
            if (categoryId != null) {
                lifecycleScope.launch {
                    val category = database.categoryDao().getCategoryById(categoryId)
                    if (category != null) {
                        binding.noteCategory.text = category.name  // Zobrazíme název kategorie
                    } else {
                        binding.noteCategory.text = "Neznámá kategorie"
                    }
                }
            } else {
                binding.noteCategory.text = "Bez kategorie"  // Pokud není přiřazena žádná kategorie
            }

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