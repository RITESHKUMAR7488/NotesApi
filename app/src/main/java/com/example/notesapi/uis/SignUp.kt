package com.example.notesapi.uis

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.notesapi.MyApplication
import com.example.notesapi.R
import com.example.notesapi.apiInterface.NotesApiInterface
import com.example.notesapi.databinding.ActivitySignUpBinding
import com.example.notesapi.models.User
import com.example.notesapi.repositories.Repository
import com.example.notesapi.viewModelFactory.MyViewModelFactory
import com.example.notesapi.viewModels.MyViewModel

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        val application = application as MyApplication
        val retrofitBuilder = application.retrofit
        val apiInterface = retrofitBuilder.create(NotesApiInterface::class.java)
        val repository= Repository(apiInterface)
        viewModel= ViewModelProvider(this, MyViewModelFactory(repository))[MyViewModel::class.java]
        with(binding){

            val user= User()
            val name=binding.etName.text.toString()
            val email=binding.etEmail.text.toString()
            val password=binding.etPassword.text.toString()
            user.username=name
            user.email=email
            user.password=password

            viewModel.signUp(user).observe(this@SignUp){
                Log.d("Dataaa",it.toString())


            }


        }


    }
}