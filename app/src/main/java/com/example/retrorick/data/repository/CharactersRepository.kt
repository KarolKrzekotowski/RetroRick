package com.example.retrorick.data.repository

import android.content.Context
import com.example.retrorick.data.db.CharacterDatabase
import com.example.retrorick.data.model.SerialCharacter
import com.example.retrorick.data.remote.RemoteSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Response

class CharactersRepository(context: Context) {

    private val dao = CharacterDatabase.getInstance(context).characterDao()
    private val api = RemoteSource.api

    suspend fun loadCharacter(id:Int): Response<SerialCharacter>{
        return api.getCharacter(id)
    }
    fun getCharacter(id: Int): Flow<SerialCharacter>{
        return dao.getCharacter(id)
    }

    suspend fun insertAll(list: List<SerialCharacter>){
        withContext(Dispatchers.IO){
            dao.insert(list)
        }
    }



}