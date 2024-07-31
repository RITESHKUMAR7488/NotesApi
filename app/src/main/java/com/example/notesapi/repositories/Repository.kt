package com.example.notesapi.repositories

// Import necessary libraries and custom models
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.notesapi.apiInterface.NotesApiInterface
import com.example.notesapi.models.ApiResponseSignupModel
import com.example.notesapi.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Repository class for handling API calls
class Repository(private val api: NotesApiInterface) {

    // Function to handle user signup
    fun signUp(user: User, data: MutableLiveData<ApiResponseSignupModel>) {
        // Call the signUp method from the API interface and enqueue the request
        api.signUp(user).enqueue(object : Callback<ApiResponseSignupModel> {
            // Handle the API response
            override fun onResponse(
                call: Call<ApiResponseSignupModel>,
                response: Response<ApiResponseSignupModel>
            ) {
                // Set the response body to the MutableLiveData object
                data.value = response.body()
            }

            // Handle any errors that occur during the API call
            override fun onFailure(call: Call<ApiResponseSignupModel>, t: Throwable) {
                // Log the error message
                Log.d("Error", t.message.toString())
            }
        })
    }

    // Function to handle user signin
    fun signIn(user: User, data: MutableLiveData<ApiResponseSignupModel>) {
        // Call the signIn method from the API interface and enqueue the request
        api.signIn(user).enqueue(object : Callback<ApiResponseSignupModel> {
            // Handle the API response
            override fun onResponse(
                call: Call<ApiResponseSignupModel>,
                response: Response<ApiResponseSignupModel>
            ) {
                // Set the response body to the MutableLiveData object
                data.value = response.body()
            }

            // Handle any errors that occur during the API call
            override fun onFailure(call: Call<ApiResponseSignupModel>, t: Throwable) {
                // Log the error message
                Log.d("Error", t.message.toString())
            }
        })
    }
}




