package com.example.wzker.api

import com.example.wzker.util.Quran_BASE_URL
import com.example.wzker.util.baseUrl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object QuranApi {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(Quran_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()).build()
    }

    val api: QuranService by lazy {
        retrofit.create(QuranService::class.java)
    }
}