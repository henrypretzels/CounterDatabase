package com.example.counterdatabase.ui.crates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.counterdatabase.data.Crate
import com.example.counterdatabase.data.CratesRepository
import kotlinx.coroutines.launch

class CratesViewModel : ViewModel() {

    private val repository = CratesRepository()

    private val _crates = MutableLiveData<List<Crate>>()
    val crates: LiveData<List<Crate>> = _crates

    private var allCrates: List<Crate> = emptyList()

    fun getCrates() {
        viewModelScope.launch {
            try {
                val result = repository.getCrates()
                allCrates = result
                _crates.value = result
            } catch (e: Exception) {
                android.util.Log.e("CratesViewModel", "Error getting crates", e)
                _crates.value = emptyList()
            }
        }
    }

    fun searchCrates(query: String?) {
        if (query.isNullOrEmpty()) {
            _crates.value = allCrates
        } else {
            _crates.value = allCrates.filter { it.name.contains(query, ignoreCase = true) }
        }
    }
}