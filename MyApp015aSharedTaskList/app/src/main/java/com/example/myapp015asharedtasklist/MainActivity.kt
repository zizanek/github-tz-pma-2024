package com.example.myapp015asharedtasklist

import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp015asharedtasklist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tasks = mutableListOf<Task>() // Lokální seznam úkolů
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializace RecyclerView
        taskAdapter = TaskAdapter(tasks) { task ->
            updateTask(task) // Callback pro změnu úkolu
        }
        binding.recyclerViewTasks.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTasks.adapter = taskAdapter

        // Nastavení logiky pro FloatingActionButton
        binding.fabAddTask.setOnClickListener {
            showAddTaskDialog()
        }

        // Simulace načtení dat
        loadTasks()
    }

    private fun loadTasks() {
        tasks.add(Task("1", "Buy groceries", isCompleted = false, assignedTo = "Alice"))
        tasks.add(Task("2", "Clean the house", isCompleted = false, assignedTo = ""))
        tasks.add(Task("3", "Prepare presentation", isCompleted = true, assignedTo = "Bob"))
        taskAdapter.notifyDataSetChanged()
    }

    private fun updateTask(task: Task) {
        // Tady později napojíme Firestore update
        println("Task updated: ${task.name}, completed: ${task.isCompleted}")
    }

    private fun showAddTaskDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add Task")

        // Vytvoření vstupního pole
        val input = EditText(this)
        input.hint = "Task name"
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        // Tlačítka dialogu
        builder.setPositiveButton("Add") { _, _ ->
            val taskName = input.text.toString()
            if (taskName.isNotBlank()) {
                addTask(taskName)
            } else {
                Toast.makeText(this, "Task name cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    private fun addTask(name: String) {
        val newTask = Task(
            id = (tasks.size + 1).toString(), // Generujeme jednoduché ID
            name = name,
            isCompleted = false,
            assignedTo = "" // Zatím nepřiřazené
        )
        tasks.add(newTask)
        taskAdapter.notifyItemInserted(tasks.size - 1)
    }

}