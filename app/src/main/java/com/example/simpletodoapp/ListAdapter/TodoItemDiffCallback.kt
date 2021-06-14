package com.example.simpletodoapp.ListAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.simpletodoapp.Todo

class UserItemDiffCallback : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean = oldItem == newItem

}