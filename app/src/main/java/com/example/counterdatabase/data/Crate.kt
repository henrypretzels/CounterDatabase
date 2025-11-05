package com.example.counterdatabase.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Crate(
    val id: String,
    val name: String,
    val description: String?,
    val type: String?,
    val first_sale_date: String?,
    val contains: List<ContainedItem>?,
    val contains_rare: List<ContainedItem>?,
    val image: String,
    val loot_list: LootList?
) : Parcelable