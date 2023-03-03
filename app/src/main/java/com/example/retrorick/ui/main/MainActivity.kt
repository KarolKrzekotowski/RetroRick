package com.example.retrorick.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.retrorick.data.model.SerialCharacter
import com.example.retrorick.databinding.ActivityMainBinding
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var character: SerialCharacter? = null
        binding.id.addTextChangedListener {

            try {

                val id = binding.id.text.toString().toInt()
                viewModel.performFetchSingleCharacter(id)


            } catch (e: Exception) {

            }
        }

        lifecycleScope.launch {


                viewModel.character.collect() {
                    character = viewModel.character.value
                    Log.i("SIEMANO",character.toString())
                    referesh(character)
                    delay(1000)
                    Log.i("SIEMANO",character.toString())
                }



        }
    }


    fun referesh(character: SerialCharacter?) {
        with(binding) {
            created.text = character?.created
            species.text = character?.species
            status.text = character?.status
            gender.text = character?.gender
            name.text = character?.name
            type.text = character?.type
            Picasso.get()
                .load(character?.image)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .into(image)

        }
    }
}

