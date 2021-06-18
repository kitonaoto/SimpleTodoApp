package com.example.simpletodoapp

import android.R
import android.R.id
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.simpletodoapp.databinding.ActivityMainBinding
import com.example.simpletodoapp.databinding.FragmentNewTodoBinding
import com.example.simpletodoapp.databinding.FragmentNewTodoBinding.inflate
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NewTodo : Fragment() {

    lateinit var viewModel: TodoViewModel
    private lateinit var binding: FragmentNewTodoBinding
    private val spinnerItems = arrayOf("勉強", "買い物", "仕事")

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        activity?.run {
            viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        }

        binding = inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)


        binding.dateTitle.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    DatePickerDialog.OnDateSetListener() {view, year, month, dayOfMonth->
                        binding.dateContent.text = "${year}/${month + 1}/${dayOfMonth}"
                    },
                    2020,
                    3,
                    1)
            datePickerDialog.show()
        }

        binding.sendButton.setOnClickListener{
            if(binding.editText.text != null) {
                var inputTitle = binding.editText.text
                var inputCategory = binding.categoryContent.text
                var inputDate = binding.dateContent.text
                viewModel.todoListLiveData.apply {
                    value?.add(Todo(title = inputTitle.toString(), category = inputCategory as String, date = inputDate as String))
                }
                // 入力文字を消す
                binding.editText.text = null
                // TODO(画面遷移させる)
            }
        }

        binding.categoryTitle.setOnClickListener {
            AlertDialog.Builder(requireContext())
                    .setTitle("title")
                    .setItems(spinnerItems) { _, which ->
                        binding.categoryContent.text = spinnerItems[which]
                    }
                    .show()
        }
    }
}