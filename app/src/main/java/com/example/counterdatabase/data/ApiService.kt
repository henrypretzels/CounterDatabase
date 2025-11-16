package com.example.counterdatabase.data

import retrofit2.http.GET

interface ApiService {
    @GET("skins.json")
    suspend fun getSkins(): List<Skin>
    
    @GET("stickers.json")
    suspend fun getStickers(): List<Sticker>

    @GET("highlights.json")
    suspend fun getHighlights(): List<Highlight>

    @GET("crates.json")
    suspend fun getCrates(): List<Crate>

    @GET("agents.json")
    suspend fun getAgents(): List<Agent>
}