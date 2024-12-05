package com.example.myapp014amynotehub

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    // Vloží novou poznámku do databáze
    @Insert
    suspend fun insert(note: Note)

    // Aktualizuje existující poznámku
    @Update
    suspend fun update(note: Note)

    // Smaže zadanou poznámku
    @Delete
    suspend fun delete(note: Note)

    // Načte všechny poznámky a vrátí je jako Flow, které umožňuje pozorování změn
    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun getAllNotes(): Flow<List<Note>>

    // Vymaže všechny záznamy z tabulky
    @Query("DELETE FROM note_table")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM note_table WHERE categoryId = :categoryId")
    fun getNotesByCategoryId(categoryId: Int): Flow<List<Note>>
}