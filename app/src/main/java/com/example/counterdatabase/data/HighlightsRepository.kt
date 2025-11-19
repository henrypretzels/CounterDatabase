package com.example.counterdatabase.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HighlightsRepository {
    suspend fun getHighlights(): List<Highlight> = withContext(Dispatchers.IO) {
        try {
            RetrofitInstance.api.getHighlights()
        } catch (e: Exception) {
            Log.e("HighlightsRepository", "Error fetching highlights", e)
            emptyList()
        }
    }
}