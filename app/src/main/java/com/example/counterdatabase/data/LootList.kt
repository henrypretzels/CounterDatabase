package com.example.counterdatabase.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LootList(
    val name: String?,
    val footer: String?,
    val image: String?
) : Parcelable