package com.example.myapp014amynotehub

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")

data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // ID poznámky, automaticky generované
    val title: String,  // Název poznámky
    val content: String,  // Obsah poznámky
    val categoryId: Int? = null  // Volitelný odkaz na kategorii
)
