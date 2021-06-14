package com.example.simpletodoapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel: ViewModel() {
    var todoListLiveData: MutableLiveData<MutableList<Todo>> = MutableLiveData()
}