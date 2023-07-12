package com.example.test2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R

class MainFragment : Fragment() {
    private lateinit var number1EditText: EditText
    private lateinit var number2EditText: EditText
    private lateinit var nextButton: Button
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        number1EditText = view.findViewById(R.id.number1EditText)
        number2EditText = view.findViewById(R.id.number2EditText)
        nextButton = view.findViewById(R.id.nextButton)

        nextButton.setOnClickListener {
            val number1 = number1EditText.text.toString().toIntOrNull()
            val number2 = number2EditText.text.toString().toIntOrNull()

            if (number1 != null && number2 != null) {
                viewModel.fetchData(number1, number2)
            } else {
                Toast.makeText(requireContext(), "Введите корректные числа", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            nextButton.isEnabled = !isLoading
            if (isLoading) {
                nextButton.text = "Загрузка..."
            } else {
                nextButton.text = "Далее"
            }
        }

        viewModel.navigateToSecondScreen.observe(viewLifecycleOwner) { navigate ->
            if (navigate) {
                (activity as? MainActivity)?.navigateToSecondFragment()
            }
        }
    }
}