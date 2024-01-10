package com.example.wzker.quran

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TafseerResponse(
    @Json(name = "text") val text: String
)
