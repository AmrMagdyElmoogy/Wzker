package com.example.wzker.quran

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SurahResponse(
    @Json(name = "sura_index") val suraIndex: Int,
    @Json(name = "sura_name") val suraName: String,
    @Json(name = "ayah_number") val ayahNumber: Int,
    @Json(name = "text") val text: String,
)
