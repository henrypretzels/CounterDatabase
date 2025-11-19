package com.example.counterdatabase.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AgentsRepository {
    suspend fun getAgents(): List<Agent> = withContext(Dispatchers.IO) {
        try {
            Log.d("AgentsRepository", "Starting API call for agents")
            val result = RetrofitInstance.api.getAgents()
            Log.d("AgentsRepository", "Agents fetched successfully: ${result.size} items")
            result
        } catch (e: retrofit2.HttpException) {
            Log.e("AgentsRepository", "HTTP Error: ${e.code()}", e)
            emptyList()
        } catch (e: Exception) {
            Log.e("AgentsRepository", "Error fetching agents", e)
            emptyList()
        }
    }
}

