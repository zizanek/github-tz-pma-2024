package com.example.myapp014amynotehub

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {

    // Vloží nový štítek do databáze
    @Insert
    suspend fun insert(tag: Tag)

    // Aktualizuje existující štítek
    @Update
    suspend fun update(tag: Tag)

    // Smaže zadaný štítek
    @Delete
    suspend fun delete(tag: Tag)

    // Načte všechny štítky a vrátí je jako Flow
    @Query("SELECT * FROM tag_table ORDER BY name ASC")
    fun getAllTags(): Flow<List<Tag>>
}