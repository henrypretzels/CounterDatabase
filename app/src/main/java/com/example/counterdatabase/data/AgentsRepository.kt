package com.example.counterdatabase.data

class AgentsRepository {
    suspend fun getAgents() = RetrofitInstance.api.getAgents()
}
