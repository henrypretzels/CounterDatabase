package com.example.counterdatabase.ui.skins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.counterdatabase.data.Skin
import com.example.counterdatabase.data.SkinsRepository
import kotlinx.coroutines.launch

class SkinsViewModel : ViewModel() {

    private val repository = SkinsRepository()

    private val _skins = MutableLiveData<List<Skin>>()
    val skins: LiveData<List<Skin>> = _skins

    private var allSkins: List<Skin> = emptyList()

    fun getSkins() {
        android.util.Log.d("SkinsViewModel", "getSkins() called")
        viewModelScope.launch {
            try {
                android.util.Log.d("SkinsViewModel", "Calling repository.getSkins()...")
                val result = repository.getSkins()
                android.util.Log.d("SkinsViewModel", "Repository returned ${result.size} skins")
                allSkins = result
                _skins.value = result
                android.util.Log.d("SkinsViewModel", "LiveData updated with ${result.size} skins")
            } catch (e: Exception) {
                android.util.Log.e("SkinsViewModel", "Error getting skins", e)
                e.printStackTrace()
                _skins.value = emptyList()
            }
        }
    }

    fun searchSkins(query: String?) {
        if (query.isNullOrEmpty()) {
            _skins.value = allSkins
        } else {
            _skins.value = allSkins.filter { it.name.contains(query, ignoreCase = true) }
        }
    }
}