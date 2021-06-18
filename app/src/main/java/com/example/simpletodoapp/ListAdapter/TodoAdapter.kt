package com.example.simpletodoapp.ListAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.simpletodoapp.R
import com.example.simpletodoapp.Todo

class UsersAdapter : ListAdapter<Todo, TodoViewHolder>(UserItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.todolist_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        // holder.bindTo(getItem(position))
        holder.todoTitle.text = getItem(position).title
        holder.todoCategory.text = getItem(position).category
        holder.todoDate.text = getItem(position).date
    }

}