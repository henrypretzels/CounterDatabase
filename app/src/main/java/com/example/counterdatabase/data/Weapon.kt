package com.example.counterdatabase.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weapon(
    val id: String,
    val name: String
) : Parcelable