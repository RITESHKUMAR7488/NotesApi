package com.example.notesapi.apiInterface

import com.example.notesapi.models.ApiResponseSignupModel
import com.example.notesapi.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface NotesApiInterface {
    @POST("users/signUp")
    fun signUp(@Body user: User): Call<ApiResponseSignupModel>

    @POST("users/signIn")
    fun signIn(@Body user: User): Call<ApiResponseSignupModel>

}