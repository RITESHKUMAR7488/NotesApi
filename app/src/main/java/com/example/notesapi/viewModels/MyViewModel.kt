package com.example.notesapi.viewModels

// Importing necessary Android architecture components and custom models
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapi.models.ApiResponseNotesModel
import com.example.notesapi.models.ApiResponseSignupModel
import com.example.notesapi.models.User
import com.example.notesapi.repositories.Repository

// ViewModel class for handling signup and signin operations
class MyViewModel(private val repository: Repository) : ViewModel() {

    // Function to handle user signup
    fun signUp(user: User): MutableLiveData<ApiResponseSignupModel> {
        // Creating a MutableLiveData object to hold the response from the signup operation
        val data = MutableLiveData<ApiResponseSignupModel>()

        // Calling the repository's signUp function with the user data and MutableLiveData object
        repository.signUp(user, data)

        // Returning the MutableLiveData object which will hold the signup response
        return data
    }

    // Function to handle user signin
    fun signIn(user: User): MutableLiveData<ApiResponseSignupModel> {
        // Creating a MutableLiveData object to hold the response from the signin operation
        val data = MutableLiveData<ApiResponseSignupModel>()

        // Calling the repository's signIn function with the user data and MutableLiveData object
        repository.signIn(user, data)

        // Returning the MutableLiveData object which will hold the signin response
        return data
    }
    fun getNotes(): MutableLiveData<ApiResponseNotesModel> {
        val data = MutableLiveData<ApiResponseNotesModel>()
        repository.getNotes(data)
        return data
    }
}
