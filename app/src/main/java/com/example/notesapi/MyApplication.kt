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

            // Retrieve the token from SharedPreferences
            val sharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE)
            val token = sharedPreferences.getString("token", null) ?: ""

            // Create a new request with the header
            val newRequest: Request = originalRequest.newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer $token"
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