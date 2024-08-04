package com.example.notesapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.notesapi.adapters.MainPageAdapter
import com.example.notesapi.apiInterface.NotesApiInterface
import com.example.notesapi.databinding.ActivityMainBinding
import com.example.notesapi.repositories.Repository
import com.example.notesapi.uis.CreateNotes
import com.example.notesapi.uis.Description
import com.example.notesapi.viewModelFactory.MyViewModelFactory
import com.example.notesapi.viewModels.MyViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        val application = application as MyApplication
        val retrofitBuilder = application.retrofit
        val apiInterface = retrofitBuilder.create(NotesApiInterface::class.java)
        val repository= Repository(apiInterface)
        viewModel= ViewModelProvider(this, MyViewModelFactory(repository))[MyViewModel::class.java]
        with(binding){
            viewModel.getNotes().observe(this@MainActivity){
                val adapter= MainPageAdapter(it.response)
                recyclerView.adapter=adapter
                Log.d("Dataeaa",it.toString())

                btnCreate.setOnClickListener{
                    val intent= Intent(this@MainActivity,CreateNotes::class.java)
                    startActivity(intent)
                }


            }





        }



    }
}