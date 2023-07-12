package com.example.test2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R

class SecondActivity : AppCompatActivity() {
    private lateinit var backButton: Button
    private lateinit var sumTextView: TextView
    private lateinit var dataTextView: TextView
    private lateinit var ageTextView: TextView

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        backButton = findViewById(R.id.backButton)
        sumTextView = findViewById(R.id.sumTextView)
        dataTextView = findViewById(R.id.dataTextView)
        ageTextView = findViewById(R.id.ageTextView)


        val userRepository = UserRepository()
        val viewModelFactory = MainViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


        viewModel.sum.observe(this) { sum ->
            sumTextView.text = "Сумма чисел: $sum"
        }

        viewModel.data.observe(this) { userList ->
            val names = userList.map { it.name }
            val ages = userList.map { it.age.toString() }
            dataTextView.text = names.joinToString("\n")
            ageTextView.text = ages.joinToString("\n")
        }

        backButton.setOnClickListener {
            finish()
        }

        val number1 = intent.getIntExtra("number1", 0)
        val number2 = intent.getIntExtra("number2", 0)

        viewModel.fetchData(number1, number2)
    }
}
