package com.example.wzker.hadith

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HadithResponse(
    val code: Int,
    val data: HadithData
)

@JsonClass(generateAdapter = true)
data class HadithData(
    val id: String,
    val contents: HadithContents
)

@JsonClass(generateAdapter = true)
data class HadithContents(
    val arab: String
)
