package com.example.counterdatabase.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Skin(
    val id: String,
    val name: String,
    val description: String?,
    val weapon: Weapon,
    val category: Category,
    val pattern: Pattern,
    val min_float: Float,
    val max_float: Float,
    val rarity: Rarity,
    val stattrak: Boolean,
    val image: String
) : Parcelable