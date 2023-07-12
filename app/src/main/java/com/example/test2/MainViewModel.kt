package com.example.test2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _navigateToSecondScreen = MutableLiveData<Boolean>()
    val navigateToSecondScreen: LiveData<Boolean> = _navigateToSecondScreen

    private val _sum = MutableLiveData<Int>()
    val sum: LiveData<Int> = _sum

    private val _data = MutableLiveData<List<User>>()
    val data: LiveData<List<User>> = _data

    fun fetchData(number1: Int, number2: Int) {
        _loading.value = true

        viewModelScope.launch {
            delay(2000) // Симуляция задержки загрузки данных

            _sum.value = number1 + number2

            val loadedData = userRepository.fetchDataFromUrl()

            _data.value = loadedData

            _loading.value = false
            _navigateToSecondScreen.value = true
        }
    }
}


