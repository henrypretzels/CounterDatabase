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
        viewModelScope.launch {
            val result = repository.getSkins()
            allSkins = result
            _skins.value = result
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