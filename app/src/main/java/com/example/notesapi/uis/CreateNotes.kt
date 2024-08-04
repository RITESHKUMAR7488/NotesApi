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
import com.example.notesapi.databinding.ActivityCreateNotesBinding
import com.example.notesapi.models.ApiResponseNotesCreateModel
import com.example.notesapi.models.NotesModel
import com.example.notesapi.repositories.Repository
import com.example.notesapi.viewModelFactory.MyViewModelFactory
import com.example.notesapi.viewModels.MyViewModel

class CreateNotes : AppCompatActivity() {
    private lateinit var viewModel: MyViewModel
    private lateinit var binding: ActivityCreateNotesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= DataBindingUtil.setContentView(this,R.layout.activity_create_notes)
        val application = application as MyApplication
        val retrofitBuilder = application.retrofit
        val apiInterface = retrofitBuilder.create(NotesApiInterface::class.java)
        val repository= Repository(apiInterface)
        viewModel= ViewModelProvider(this, MyViewModelFactory(repository))[MyViewModel::class.java]
        with(binding){
            btnBack.setOnClickListener {
                finish()
            }
            btnSave.setOnClickListener {
                createNotes()
            }
        }


    }
    private fun createNotes(){
        val response= NotesModel()
        val title=binding.tvTitle.text.toString()
        val description=binding.tvDescription.text.toString()
        response.title=title
        response.description=description

        viewModel.createNotes(response).observe(this@CreateNotes){
            Log.d("Dataaa",it.toString())
            if(it.success==true){
                Toast.makeText(this@CreateNotes, it.message, Toast.LENGTH_SHORT).show()
                val intent= Intent(this@CreateNotes, MainActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this@CreateNotes, it.message, Toast.LENGTH_SHORT).show()
                Log.d("Error",it.message.toString())
            }


        }

    }
}