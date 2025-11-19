package com.example.counterdatabase

import androidx.annotation.DrawableRes

data class MenuItem(
    val title: String,
    val subtitle: String,
    @DrawableRes val iconRes: Int
)
