package com.example.notesapi.uis

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
import com.example.notesapi.databinding.ActivityDescriptionBinding
import com.example.notesapi.models.NotesModel
import com.example.notesapi.repositories.Repository
import com.example.notesapi.viewModelFactory.MyViewModelFactory
import com.example.notesapi.viewModels.MyViewModel

class Description : AppCompatActivity() {
    private lateinit var binding: ActivityDescriptionBinding
    private lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= DataBindingUtil.setContentView(this,R.layout.activity_description)
        val application = application as MyApplication
        val retrofitBuilder = application.retrofit
        val apiInterface = retrofitBuilder.create(NotesApiInterface::class.java)
        val repository= Repository(apiInterface)
        viewModel= ViewModelProvider(this, MyViewModelFactory(repository))[MyViewModel::class.java]
        with(binding){
            tvTitle.setText(intent.getStringExtra("title"))
            tvDescription.setText(intent.getStringExtra("description"))
            btnEdit.setOnClickListener {
                btnEdit.visibility=Button.GONE
                btnSave.visibility=Button.VISIBLE
                tvTitle.isFocusableInTouchMode=true
                tvDescription.isFocusableInTouchMode=true

            }
            btnBack.setOnClickListener {
                finish()
            }
            btnSave.setOnClickListener {
                updateNotes()
            }
            btnDelete.setOnClickListener {
                deleteNotes()
//                val artDialog=android.app.AlertDialog.Builder(this@Description)
//                artDialog.setTitle("Delete")
//                artDialog.setMessage("Are you sure you want to delete this note?")
//                artDialog.setPositiveButton("Yes"){_,_ ->
//                    deleteNotes()
//
//                }
////                artDialog.setNegativeButton("No"){_,_ ->
//                    Toast.makeText(this@Description, "Cancelled", Toast.LENGTH_SHORT).show()
//                }


            }





        }
    }
    private fun updateNotes(){
        val response= NotesModel()
        val title=binding.tvTitle.text.toString()
        val description=binding.tvDescription.text.toString()
        response.title=title
        response.description=description
        response._id=intent.getStringExtra("notesId").toString()

        viewModel.updateNotes(response).observe(this@Description){
            Log.d("Dataaa",it.toString())
            if(it.success==true){
                Toast.makeText(this@Description, it.message, Toast.LENGTH_SHORT).show()
                val intent= Intent(this@Description, MainActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this@Description, it.message, Toast.LENGTH_SHORT).show()
                Log.d("Error",it.message.toString())
            }


        }

    }
    private fun deleteNotes(){
        val notesId=intent.getStringExtra("notesId").toString()


        viewModel.deleteNotes(notesId).observe(this@Description){
            Log.d("Dataaa",it.toString())
            if(it.success==true){
                Toast.makeText(this@Description, it.message, Toast.LENGTH_SHORT).show()
                val intent= Intent(this@Description, MainActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this@Description, it.message, Toast.LENGTH_SHORT).show()
                Log.d("Error",it.message.toString())
            }


        }

    }
}