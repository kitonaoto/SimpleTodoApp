package com.example.simpletodoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletodoapp.ListAdapter.UsersAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class IndexTodo : Fragment() {

    lateinit var todoViewModel: TodoViewModel
    var adapter = UsersAdapter()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        activity?.run {
            todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_index_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var recyclerView: RecyclerView = view.findViewById(R.id.recyclerview)

        todoViewModel.todoListLiveData.observe(viewLifecycleOwner, Observer {list->
            adapter.submitList(list)
        })

        recyclerView.setAdapter(adapter)

        view.findViewById<FloatingActionButton>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}