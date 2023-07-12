package com.example.test2

class UserRepository {
    suspend fun fetchDataFromUrl(): List<User> {
        // Загрузка данных по ссылке и обработка полученных данных
        // В данном примере возвращаем тестовые данные
        return listOf(
            User("Алексей", 18),
            User("Тимур", 20),
            User("Дмитрий", 21)
        )
    }
}