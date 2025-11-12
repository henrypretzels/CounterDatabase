package com.example.counterdatabase.ui.stickers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.counterdatabase.data.Sticker
import com.example.counterdatabase.data.StickersRepository
import kotlinx.coroutines.launch

class StickersViewModel : ViewModel() {

    private val repository = StickersRepository()

    private val _stickers = MutableLiveData<List<Sticker>>()
    val stickers: LiveData<List<Sticker>> = _stickers

    var allStickers: List<Sticker> = emptyList()

    fun getStickers() {
        viewModelScope.launch {
            val result = repository.getStickers()
            allStickers = result
            _stickers.value = result
        }
    }

    fun searchStickers(query: String?) {
        if (query.isNullOrEmpty()) {
            _stickers.value = allStickers
        } else {
            _stickers.value = allStickers.filter {
                it.name.contains(query, ignoreCase = true) ||
                it.tournament_team?.contains(query, ignoreCase = true) == true ||
                it.tournament_event?.contains(query, ignoreCase = true) == true
            }
        }
    }
}
