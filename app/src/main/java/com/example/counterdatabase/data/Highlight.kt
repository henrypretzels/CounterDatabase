package com.example.counterdatabase.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Highlight(
    val id: String,
    val name: String,
    val description: String?,
    val tournament_event: String?,
    val team0: String?,
    val team1: String?,
    val stage: String?,
    val map: String?,
    val market_hash_name: String?,
    val image: String,
    val video: String?
) : Parcelable