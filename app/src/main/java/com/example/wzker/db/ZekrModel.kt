package com.example.wzker.db

import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.MutableStateFlow

@Entity(tableName = "Azkar")
data class ZekrModel(
    @PrimaryKey val id: Int,
    @StringRes val primaryZekar: Int,
    @StringRes val description: Int,
    val allCount: Int,
)

