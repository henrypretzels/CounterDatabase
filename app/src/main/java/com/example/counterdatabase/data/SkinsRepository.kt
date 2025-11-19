package com.example.counterdatabase.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SkinsRepository {
    suspend fun getSkins(): List<Skin> = withContext(Dispatchers.IO) {
        try {
            Log.d("SkinsRepository", "=== Starting API call ===")
            Log.d("SkinsRepository", "Base URL: https://raw.githubusercontent.com/ByMykel/CSGO-API/main/public/api/en/")
            Log.d("SkinsRepository", "Endpoint: skins.json")
            
            val result = RetrofitInstance.api.getSkins()
            
            Log.d("SkinsRepository", "=== API Response Received ===")
            Log.d("SkinsRepository", "Skins fetched successfully: ${result.size} items")
            
            if (result.isEmpty()) {
                Log.w("SkinsRepository", "WARNING: API returned empty list!")
            } else {
                Log.d("SkinsRepository", "First skin: ${result.firstOrNull()?.name ?: "null"}")
            }
            
            result
        } catch (e: retrofit2.HttpException) {
            Log.e("SkinsRepository", "HTTP Error: ${e.code()}", e)
            Log.e("SkinsRepository", "Response: ${e.response()?.errorBody()?.string()}")
            e.printStackTrace()
            emptyList()
        } catch (e: java.net.UnknownHostException) {
            Log.e("SkinsRepository", "Network Error: Cannot reach host", e)
            e.printStackTrace()
            emptyList()
        } catch (e: java.net.SocketTimeoutException) {
            Log.e("SkinsRepository", "Timeout Error: Request took too long", e)
            e.printStackTrace()
            emptyList()
        } catch (e: Exception) {
            Log.e("SkinsRepository", "Unexpected Error fetching skins", e)
            Log.e("SkinsRepository", "Error type: ${e.javaClass.simpleName}")
            Log.e("SkinsRepository", "Error message: ${e.message}")
            e.printStackTrace()
            emptyList()
        }
    }
}