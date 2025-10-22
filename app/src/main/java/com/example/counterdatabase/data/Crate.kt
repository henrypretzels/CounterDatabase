package com.example.counterdatabase.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Crate(
    val id: String,
    val name: String,
    val image: String
) : Parcelable
