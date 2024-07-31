package com.example.notesapi

import android.app.Application
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {
    // Declare a lateinit variable for Retrofit
    lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()

        // Create an Interceptor to add a header to every request
        val headerInterceptor = Interceptor { chain ->
            // Get the original request
            val originalRequest: Request = chain.request()
            // Create a new request with the additional header
            val newRequest: Request = originalRequest.newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InhAeXouY29tIiwiaWQiOiI2NjRkNmI3OGE2YWZmMzZmODhkOGM5ZTYiLCJpYXQiOjE3MTg4Njk3NTV9.esKJmE0LdgHqVs6XkSumJ1ALoGbeyqrQLDNwQdKCaSg"
                )
                .build()
            // Proceed with the new request
            chain.proceed(newRequest)
        }

        // Create an OkHttpClient and add the interceptor
        val client = OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .build()

        // Initialize Retrofit with the base URL, OkHttpClient, and Gson converter
        retrofit = Retrofit.Builder()
            .baseUrl("https://notes.jestitech.com/") // Base URL for the API
            .client(client) // Set the custom OkHttpClient
            .addConverterFactory(GsonConverterFactory.create()) // Add Gson converter
            .build()
    }
}