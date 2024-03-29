package com.example.wzker.api

import com.example.wzker.hadith.HadithResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HadithService {

    @GET("{endpoint}/{number}")
    suspend fun getRandomHadith(
       @Path("endpoint") endpoint: String,
       @Path("number") number: Int
    ): Response<HadithResponse>
}