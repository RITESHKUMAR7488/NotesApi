package com.example.notesapi.uis

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.notesapi.MainActivity
import com.example.notesapi.R
import com.example.notesapi.databinding.ActivityFirstScreenBinding

class FirstScreen : AppCompatActivity() {
    private lateinit var binding: ActivityFirstScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val sharedpref=getSharedPreferences("MyPref", MODE_PRIVATE)
        val loggedIn=sharedpref.getBoolean("loggedIn",false)
        if(loggedIn){
            val intent = Intent(this@FirstScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_first_screen)
        with(binding){
            tvSignUp.setOnClickListener {
                val intent = Intent(this@FirstScreen, SignUp::class.java)
                startActivity(intent)


            }
            tvSignIn.setOnClickListener {
                val intent = Intent(this@FirstScreen, SignIn::class.java)
                startActivity(intent)
            }
        }


    }
}