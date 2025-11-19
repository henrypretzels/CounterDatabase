package com.example.counterdatabase.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.counterdatabase.data.AgentsRepository
import com.example.counterdatabase.data.SkinsRepository
import com.example.counterdatabase.data.StickersRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val skinsRepository = SkinsRepository()
    private val stickersRepository = StickersRepository()
    private val agentsRepository = AgentsRepository()

    private val _carouselItems = MutableLiveData<List<CarouselItem>>()
    val carouselItems: LiveData<List<CarouselItem>> = _carouselItems

    fun loadCarouselData() {
        viewModelScope.launch {
            try {
                val items = mutableListOf<CarouselItem>()

                // Fetch a random skin
                skinsRepository.getSkins().randomOrNull()?.let {
                    items.add(CarouselItem(it.image, it.name))
                }

                // Fetch a random sticker
                stickersRepository.getStickers().randomOrNull()?.let {
                    items.add(CarouselItem(it.image, it.name))
                }

                // Fetch a random agent
                agentsRepository.getAgents().randomOrNull()?.let {
                    items.add(CarouselItem(it.image, it.name))
                }

                _carouselItems.postValue(items)

            } catch (e: Exception) {
                // Handle potential network errors, etc.
                _carouselItems.postValue(emptyList()) // Post an empty list on error
            }
        }
    }
}