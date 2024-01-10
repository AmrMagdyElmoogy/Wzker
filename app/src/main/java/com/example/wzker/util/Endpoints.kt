package com.example.wzker.util

import androidx.annotation.StringRes
import com.example.wzker.R

const val baseUrl = "https://api.hadith.gading.dev/books/"
const val Quran_BASE_URL = "http://api.quran-tafseer.com/"


enum class Endpoints(val endpoint: String, val upperLimit: Int) {
    MUSLIM("muslim", 4930),
    BUKHARI("bukhari", 6638),
    TIRMIDZI("tirmidzi", 3635),
    NASAI("nasai", 5364),
    ABU_DAUD("abu-daud", 4419),
    IBNU_MAJAH("ibnu-majah", 4285),
    AHMAD("ahmad", 4305),
    MALIK("malik", 1587),
}