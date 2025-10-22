package com.example.counterdatabase.data

import retrofit2.http.GET

interface ApiService {
    @GET("skins.json")
    suspend fun getSkins(): List<Skin>
    
    @GET("stickers.json")
    suspend fun getStickers(): List<Sticker>
}