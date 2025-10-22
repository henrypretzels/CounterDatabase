package com.example.counterdatabase.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tournament(
    val event: String,
    val team: String
) : Parcelable
