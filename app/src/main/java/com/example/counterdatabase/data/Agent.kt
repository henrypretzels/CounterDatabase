package com.example.counterdatabase.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Agent(
    val id: String,
    val name: String,
    val description: String?,
    val def_index: String?,
    val rarity: Rarity?,
    val collections: List<Collection>?,
    val team: Team?,
    val market_hash_name: String?,
    val image: String,
    val model_player: String?,
    val original: Original?
) : Parcelable

@Parcelize
data class Collection(
    val id: String,
    val name: String,
    val image: String?
) : Parcelable

@Parcelize
data class Team(
    val id: String,
    val name: String
) : Parcelable

@Parcelize
data class Original(
    val name: String?,
    val image_inventory: String?
) : Parcelable

