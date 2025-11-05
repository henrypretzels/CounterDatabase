package com.example.counterdatabase.data

class CratesRepository {
    suspend fun getCrates() = RetrofitInstance.api.getCrates()
}