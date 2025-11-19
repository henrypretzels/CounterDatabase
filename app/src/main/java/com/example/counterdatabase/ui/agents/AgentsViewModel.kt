package com.example.counterdatabase.ui.agents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.counterdatabase.data.Agent
import com.example.counterdatabase.data.AgentsRepository
import kotlinx.coroutines.launch

class AgentsViewModel : ViewModel() {

    private val repository = AgentsRepository()

    private val _agents = MutableLiveData<List<Agent>>()
    val agents: LiveData<List<Agent>> = _agents

    private var allAgents: List<Agent> = emptyList()

    fun getAgents() {
        viewModelScope.launch {
            try {
                val result = repository.getAgents()
                allAgents = result
                _agents.value = result
            } catch (e: Exception) {
                android.util.Log.e("AgentsViewModel", "Error getting agents", e)
                _agents.value = emptyList()
            }
        }
    }

    fun searchAgents(query: String?) {
        if (query.isNullOrEmpty()) {
            _agents.value = allAgents
        } else {
            _agents.value = allAgents.filter { it.name.contains(query, ignoreCase = true) }
        }
    }
}

