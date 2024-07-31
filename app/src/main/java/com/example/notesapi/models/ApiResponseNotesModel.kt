package com.example.notesapi.models

data class ApiResponseNotesModel(
    val code: Int,
    val message: String,
    val response: List<NotesModel>,
    val success: Boolean
)