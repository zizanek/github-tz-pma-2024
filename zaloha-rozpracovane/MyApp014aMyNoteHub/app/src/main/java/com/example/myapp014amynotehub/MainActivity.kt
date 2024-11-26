package com.example.myapp014amynotehub

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp014amynotehub.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var database: NoteHubDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializace databáze
        database = NoteHubDatabaseInstance.getDatabase(this)

        // Vložení výchozích kategorií a štítků do databáze
        //insertDefaultCategories()
        //insertDefaultTags()

        // Inicializace RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        //noteAdapter = NoteAdapter(getSampleNotes())

        //noteAdapter = NoteAdapter(emptyList()) // Inicializace s prázdným seznamem
        //binding.recyclerView.adapter = noteAdapter

        // Vložení testovacích dat
        //insertSampleNotes()

        // Načtení poznámek z databáze
        loadNotes()

        binding.fabAddNote.setOnClickListener {
            showAddNoteDialog()
        }
    }

    private fun showAddNoteDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_note, null)
        val titleEditText = dialogView.findViewById<EditText>(R.id.editTextTitle)
        val contentEditText = dialogView.findViewById<EditText>(R.id.editTextContent)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Přidat poznámku")
            .setView(dialogView)
            .setPositiveButton("Přidat") { _, _ ->
                val title = titleEditText.text.toString()
                val content = contentEditText.text.toString()
                addNoteToDatabase(title, content)
            }
            .setNegativeButton("Zrušit", null)
            .create()

        dialog.show()
    }

    private fun addNoteToDatabase(title: String, content: String) {
        lifecycleScope.launch {
            val newNote = Note(title = title, content = content)
            database.noteDao().insert(newNote)  // Vloží poznámku do databáze
            loadNotes()  // Aktualizuje seznam poznámek
        }
    }

    private fun loadNotes() {
        lifecycleScope.launch {
            database.noteDao().getAllNotes().collect { notes ->
                noteAdapter = NoteAdapter(
                    notes,
                    onDeleteClick = { note -> deleteNote(note) },
                    onEditClick = { note -> editNote(note) }
                )
                binding.recyclerView.adapter = noteAdapter
            }
        }
    }

    private fun insertSampleNotes() {
        lifecycleScope.launch {
            val sampleNotes = listOf(
                Note(title = "Vzorek 1", content = "Obsah první testovací poznámky"),
                Note(title = "Vzorek 2", content = "Obsah druhé testovací poznámky"),
                Note(title = "Vzorek 3", content = "Obsah třetí testovací poznámky")
            )
            sampleNotes.forEach { database.noteDao().insert(it) }
        }
    }

    /*private fun getSampleNotes(): List<Note> {
        // Testovací seznam poznámek
        return listOf(
            Note(title = "Poznámka 1", content = "Obsah první poznámky"),
            Note(title = "Poznámka 2", content = "Obsah druhé poznámky"),
            Note(title = "Poznámka 3", content = "Obsah třetí poznámky")
        )
    }*/

    private fun deleteNote(note: Note) {
        lifecycleScope.launch {
            database.noteDao().delete(note)  // Smazání poznámky z databáze
            loadNotes()  // Aktualizace seznamu poznámek
        }
    }

    private fun editNote(note: Note) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_note, null)
        val titleEditText = dialogView.findViewById<EditText>(R.id.editTextTitle)
        val contentEditText = dialogView.findViewById<EditText>(R.id.editTextContent)

        // Předvyplnění stávajících dat poznámky
        titleEditText.setText(note.title)
        contentEditText.setText(note.content)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Upravit poznámku")
            .setView(dialogView)
            .setPositiveButton("Uložit") { _, _ ->
                val updatedTitle = titleEditText.text.toString()
                val updatedContent = contentEditText.text.toString()

                // Aktualizace poznámky v databázi
                lifecycleScope.launch {
                    val updatedNote = note.copy(title = updatedTitle, content = updatedContent)
                    database.noteDao().update(updatedNote)  // Uloží aktualizovanou poznámku
                    loadNotes()  // Načte a aktualizuje seznam poznámek
                }
            }
            .setNegativeButton("Zrušit", null)
            .create()

        dialog.show()
    }



}