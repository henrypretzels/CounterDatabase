package com.example.counterdatabase.data

class StickersRepository {
    suspend fun getStickers() = RetrofitInstance.api.getStickers()
}
