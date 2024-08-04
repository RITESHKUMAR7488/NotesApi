package com.example.notesapi.models

data class ApiResponseNotesCreateModel(
    val code: Int,
    val message: String,
    val response: NotesModel,
    val success: Boolean
)
