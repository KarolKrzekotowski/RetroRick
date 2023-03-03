package com.example.retrorick.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retrorick.data.db.dao.CharacterDao
import com.example.retrorick.data.model.SerialCharacter

@Database(entities = [SerialCharacter::class], version = 1)
abstract class CharacterDatabase :RoomDatabase() {
    abstract fun characterDao(): CharacterDao


    companion object {
        private var db: CharacterDatabase? = null
        fun getInstance(context: Context): CharacterDatabase {
            if (db == null) {
                db = Room.databaseBuilder(
                    context,
                    CharacterDatabase::class.java,
                    "character_database"
                ).build()
            }
            return db!!
        }

    }
}