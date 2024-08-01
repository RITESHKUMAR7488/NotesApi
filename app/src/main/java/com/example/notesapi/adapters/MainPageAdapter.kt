package com.example.notesapi.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapi.databinding.RvEachItemBinding
import com.example.notesapi.models.NotesModel

class MainPageAdapter(private val data: List<NotesModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ViewHolder(
            RvEachItemBinding.inflate(
                android.view.LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return data.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item=data[position]
        val holder=holder as ViewHolder
        holder.binding.tvTitle.text=item.title
        holder.binding.tvDescription.text=item.description


    }
 inner  class ViewHolder(val binding: RvEachItemBinding):RecyclerView.ViewHolder(binding.root){

 }
}