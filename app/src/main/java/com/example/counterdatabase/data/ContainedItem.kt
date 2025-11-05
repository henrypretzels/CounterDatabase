package com.example.counterdatabase.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContainedItem(
    val id: String,
    val name: String,
    val rarity: Rarity?,
    val image: String
) : Parcelable