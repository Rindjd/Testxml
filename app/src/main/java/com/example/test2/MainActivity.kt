package com.example.test2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {
    private lateinit var number1EditText: EditText
    private lateinit var number2EditText: EditText
    private lateinit var nextButton: Button

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        number1EditText = findViewById(R.id.number1EditText)
        number2EditText = findViewById(R.id.number2EditText)
        nextButton = findViewById(R.id.nextButton)

        nextButton.setOnClickListener {
            val number1 = number1EditText.text.toString().toIntOrNull()
            val number2 = number2EditText.text.toString().toIntOrNull()

            if (number1 != null && number2 != null) {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("number1", number1)
                intent.putExtra("number2", number2)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Введите корректные числа", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loading.observe(this, Observer { isLoading ->
            nextButton.isEnabled = !isLoading
            if (isLoading) {
                nextButton.text = "Загрузка..."
            } else {
                nextButton.text = "Далее"
            }
        })

        viewModel.navigateToSecondScreen.observe(this, Observer { navigate ->
            if (navigate) {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }
        })
    }
}
