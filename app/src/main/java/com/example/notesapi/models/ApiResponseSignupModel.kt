package com.example.notesapi.models

data class ApiResponseSignupModel(
    val code: Int?=null,
    val message: String?=null,
    val response: SignUpModel?=null,
    val success: Boolean?=null
)