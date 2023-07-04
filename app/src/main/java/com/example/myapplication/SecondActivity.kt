package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.test2.MainViewModel
import com.example.test2.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SecondActivity : AppCompatActivity() {
    private lateinit var backButton: Button
    private lateinit var sumTextView: TextView
    private lateinit var dataTextView: TextView
    private lateinit var ageTextView: TextView

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        backButton = findViewById(R.id.backButton)
        sumTextView = findViewById(R.id.sumTextView)
        dataTextView = findViewById(R.id.dataTextView)
        ageTextView = findViewById(R.id.ageTextView)

        backButton.setOnClickListener {
            finish()
        }

        val number1 = intent.getIntExtra("number1", 0)
        val number2 = intent.getIntExtra("number2", 0)

        viewModel.sum.observe(this) { sum ->
            sumTextView.text = "Сумма чисел: $sum"
        }

        viewModel.data.observe(this) { userList ->
            val names = userList.map { it.name }
            val ages = userList.map { it.age.toString() }
            dataTextView.text = names.joinToString("\n")
            ageTextView.text = ages.joinToString("\n")
        }



        viewModel.fetchData(number1, number2)
    }
}


