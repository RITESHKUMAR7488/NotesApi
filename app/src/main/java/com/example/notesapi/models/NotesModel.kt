package com.example.notesapi.models

data class NotesModel(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val description: String,
    val title: String,
    val updatedAt: String,
    val userID: String
)