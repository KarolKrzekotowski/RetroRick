package com.example.retrorick.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@Entity(tableName = "Character_table")
@JsonClass(generateAdapter = true)
data class SerialCharacter(
    @Json(name = "created")
    val created: String,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "species")
    val species: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String
)