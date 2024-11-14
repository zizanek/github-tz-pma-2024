package com.example.myapp014amynotehub

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class, Category::class, Tag::class, NoteTagCrossRef::class],
    version = 1,
    exportSchema = false
)

abstract class NoteHubDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun categoryDao(): CategoryDao
    abstract fun tagDao(): TagDao
    abstract fun noteTagDao(): NoteTagDao
}