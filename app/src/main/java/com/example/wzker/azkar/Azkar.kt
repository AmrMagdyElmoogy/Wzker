package com.example.wzker.azkar

import androidx.annotation.StringRes

data class Azkar(
    val id: Int,
    val allCount: Int,
    val currentCount: Int,
    @StringRes val name: Int,
    @StringRes val description: Int,
)
