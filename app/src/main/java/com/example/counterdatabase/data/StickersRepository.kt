package com.example.counterdatabase.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StickersRepository {
    suspend fun getStickers(): List<Sticker> = withContext(Dispatchers.IO) {
        try {
            RetrofitInstance.api.getStickers()
        } catch (e: Exception) {
            Log.e("StickersRepository", "Error fetching stickers", e)
            emptyList()
        }
    }
}
