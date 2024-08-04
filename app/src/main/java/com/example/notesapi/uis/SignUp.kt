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
            btnRegister.setOnClickListener{
                signUp()
            }




        }


    }
    private fun signUp(){
        val user= User()
        val name=binding.etName.text.toString()
        val email=binding.etEmail.text.toString()
        val password=binding.etPassword.text.toString()
        user.username=name
        user.email=email
        user.password=password

        viewModel.signUp(user).observe(this@SignUp){
            Log.d("Dataaa",it.toString())
            if(it.success==true){
                Toast.makeText(this@SignUp, it.message, Toast.LENGTH_SHORT).show()

                val sharedpref=getSharedPreferences("MyPref", MODE_PRIVATE)
                val editor=sharedpref.edit()
                editor.putString("token",it.response?.token)
                editor.apply()

                val intent= Intent(this@SignUp, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this@SignUp, it.message, Toast.LENGTH_SHORT).show()
                Log.d("Error",it.message.toString())
            }


        }
    }
}