package com.example.notesapi.uis

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.notesapi.MainActivity
import com.example.notesapi.MyApplication
import com.example.notesapi.R
import com.example.notesapi.apiInterface.NotesApiInterface
import com.example.notesapi.databinding.ActivitySignInBinding
import com.example.notesapi.models.User
import com.example.notesapi.repositories.Repository
import com.example.notesapi.viewModelFactory.MyViewModelFactory
import com.example.notesapi.viewModels.MyViewModel

class SignIn : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= DataBindingUtil.setContentView(this,R.layout.activity_sign_in)
        val application = application as MyApplication
        val retrofitBuilder = application.retrofit
        val apiInterface = retrofitBuilder.create(NotesApiInterface::class.java)
        val repository= Repository(apiInterface)
        viewModel= ViewModelProvider(this, MyViewModelFactory(repository))[MyViewModel::class.java]
        with(binding){
            btnLogin.setOnClickListener{
                signIn()
            }

        }
    }
    private fun signIn(){
        val user= User()
        val email=binding.etEmail.text.toString()
        val password=binding.etPassword.text.toString()
        user.email=email
        user.password=password

        viewModel.signIn(user).observe(this@SignIn){
            Log.d("Dataaa",it.toString())
            if(it.success==true){
                val intent= Intent(this@SignIn, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this@SignIn, it.message, Toast.LENGTH_SHORT).show()
                Log.d("Error",it.message.toString())
            }


        }
    }
}