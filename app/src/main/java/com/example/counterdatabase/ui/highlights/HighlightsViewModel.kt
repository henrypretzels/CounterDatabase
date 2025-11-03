package com.example.counterdatabase.ui.highlights

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.counterdatabase.data.Highlight
import com.example.counterdatabase.data.HighlightsRepository
import kotlinx.coroutines.launch

class HighlightsViewModel : ViewModel() {

    private val repository = HighlightsRepository()

    private val _highlights = MutableLiveData<List<Highlight>>()
    val highlights: LiveData<List<Highlight>> = _highlights

    private var allHighlights: List<Highlight> = emptyList()

    fun getHighlights() {
        viewModelScope.launch {
            allHighlights = repository.getHighlights()
            _highlights.value = allHighlights
        }
    }

    fun searchHighlights(query: String?) {
        if (query.isNullOrBlank()) {
            _highlights.value = allHighlights
        } else {
            val filteredList = allHighlights.filter {
                it.name.contains(query, ignoreCase = true) ||
                it.description?.contains(query, ignoreCase = true) == true
            }
            _highlights.value = filteredList
        }
    }
}
