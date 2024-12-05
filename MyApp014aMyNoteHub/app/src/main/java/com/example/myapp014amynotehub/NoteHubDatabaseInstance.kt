package com.example.myapp014amynotehub

import android.content.Context
import androidx.room.Room

object NoteHubDatabaseInstance {

    // Lazy inicializace instance databáze
    @Volatile
    private var INSTANCE: NoteHubDatabase? = null

    // Funkce pro získání instance databáze
    fun getDatabase(context: Context): NoteHubDatabase {
        // Pokud je instance null, inicializuje ji
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NoteHubDatabase::class.java,
                "notehub_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }

}