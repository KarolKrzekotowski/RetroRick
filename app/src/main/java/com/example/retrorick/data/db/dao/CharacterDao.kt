package com.example.retrorick.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.retrorick.data.model.SerialCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(serialCharacter: List<SerialCharacter>)

    @Delete
    suspend fun delete (serialCharacter: List<SerialCharacter>)

    @Update
    suspend fun update (serialCharacter: List<SerialCharacter>)

    @Query("SELECT * FROM character_table")
    fun getAll() : Flow<List<SerialCharacter>>

    @Query("SELECT * FROM character_table WHERE id=:id")
    fun getCharacter(id:Int): Flow<SerialCharacter>

    @Query("DELETE FROM character_table")
    suspend fun dropDatabase()
}