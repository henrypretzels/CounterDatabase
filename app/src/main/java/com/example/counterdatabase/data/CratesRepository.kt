package com.example.counterdatabase.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CratesRepository {
    suspend fun getCrates(): List<Crate> = withContext(Dispatchers.IO) {
        try {
            RetrofitInstance.api.getCrates()
        } catch (e: Exception) {
            Log.e("CratesRepository", "Error fetching crates", e)
            emptyList()
        }
    }
}