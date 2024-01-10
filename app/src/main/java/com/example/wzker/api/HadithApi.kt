package com.example.wzker.api

import com.example.wzker.util.baseUrl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val retrofit: Retrofit by lazy {
    Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(MoshiConverterFactory.create()).build()
}

object HadithApi {

    val api: HadithService by lazy {
        retrofit.create(HadithService::class.java)
    }
}