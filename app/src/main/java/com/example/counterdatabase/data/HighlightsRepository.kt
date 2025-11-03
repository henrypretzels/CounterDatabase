package com.example.counterdatabase.data

class HighlightsRepository {
    suspend fun getHighlights() = RetrofitInstance.api.getHighlights()
}