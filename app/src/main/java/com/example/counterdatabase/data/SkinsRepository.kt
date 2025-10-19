package com.example.counterdatabase.data

class SkinsRepository {
    suspend fun getSkins() = RetrofitInstance.api.getSkins()
}