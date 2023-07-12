package com.example.test2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R

class SecondFragment : Fragment() {
    private lateinit var backButton: Button
    private lateinit var sumTextView: TextView
    private lateinit var dataTextView: TextView
    private lateinit var ageTextView: TextView
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        backButton = view.findViewById(R.id.backButton)
        sumTextView = view.findViewById(R.id.sumTextView)
        dataTextView = view.findViewById(R.id.dataTextView)
        ageTextView = view.findViewById(R.id.ageTextView)

        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        viewModel.sum.observe(viewLifecycleOwner) { sum ->
            sumTextView.text = "Сумма чисел: $sum"
        }

        viewModel.data.observe(viewLifecycleOwner) { userList ->
            val names = userList.map { it.name }
            val ages = userList.map { it.age.toString() }
            dataTextView.text = names.joinToString("\n")
            ageTextView.text = ages.joinToString("\n")
        }
    }
}
