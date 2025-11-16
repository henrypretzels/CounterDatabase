package com.example.counterdatabase.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Agent(
    val id: String,
    val name: String,
    val description: String?,
    val rarity: Rarity,
    val collections: List<AgentCollection>?,
    val team: AgentTeam,
    val market_hash_name: String?,
    val image: String
) : Parcelable
