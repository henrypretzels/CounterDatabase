package com.example.counterdatabase.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sticker(
    val id: String,
    val name: String,
    val description: String,
    val rarity: Rarity,
    val crates: List<Crate>,
    val tournament_event: String,
    val tournament_team: String,
    val type: String,
    val market_hash_name: String,
    val effect: String,
    val image: String
) : Parcelable
