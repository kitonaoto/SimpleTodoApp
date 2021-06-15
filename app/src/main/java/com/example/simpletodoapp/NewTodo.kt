package com.example.simpletodoapp

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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

        binding = FragmentNewTodoBinding.inflate(layoutInflater, container, false)

        val Adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        binding.categoryPicker.adapter = Adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        binding.sendButton.setOnClickListener{
            if(binding.editText.text != null) {

                var inputTitle = binding.editText.text
                var inputCategory = binding.categoryMessage.text
                var inputDate = binding.dateMessage.text
                viewModel.todoListLiveData.apply {
                    value?.add(Todo(title = inputTitle.toString(), category = inputCategory as String, date = inputDate as String))
                }

                // 入力文字を消す
                binding.editText.text = null
            }
            // debug用
            binding.textviewFirst.text = viewModel.todoListLiveData.toString()
            // viewModel.todoListLiveData.apply {
            //value?.forEach {
            //        binding.textviewFirst.text = it.title
            //    }
            //}
        }

        binding.datePicker.setOnClickListener{
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener() {view, year, month, dayOfMonth->
                    binding.dateMessage.text = "${year}/${month + 1}/${dayOfMonth}"
                },
                2020,
                3,
                1)
            datePickerDialog.show()
        }

        binding.categoryPicker.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, position: Int, id: Long) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                binding.categoryMessage.text = item
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        // ボタンを押したら一覧画面へ行くボタン
        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}