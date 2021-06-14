package com.example.simpletodoapp.ListAdapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletodoapp.R

class TodoViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val todoTitle: TextView
    val todoCategory: TextView


    init {
        // Define click listener for the ViewHolder's View.
        todoTitle = itemView.findViewById(R.id.todoTitle)
        todoCategory = itemView.findViewById(R.id.todoCategory)
    }
}