package com.example.retrorick.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrorick.data.model.SerialCharacter
import com.example.retrorick.data.repository.CharactersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CharactersRepository(application)

    private val  _character = MutableStateFlow<SerialCharacter?>(null)
    val character = _character.asStateFlow()

    fun performFetchSingleCharacter(id:Int) {
        viewModelScope.launch {
            var local:SerialCharacter?=null

            local = repository.getCharacter(id).first()


            if (local != null){
                _character.update {local}
                    return@launch
                }
            val remote = repository.loadCharacter(id)

            if (remote.isSuccessful){
                val data = remote.body()
                if (data != null){
                    _character.update { data }
                    repository.insertAll(listOf(data))
                }
            }

        }
    }



}