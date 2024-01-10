package com.example.wzker.api

import com.example.wzker.quran.SurahResponse
import com.example.wzker.quran.TafseerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranService {

    @GET("quran/{surah}/{ayah}")
    suspend fun getAyah(
        @Path("surah") surah: Int,
        @Path("ayah") ayah: Int
    ): Response<SurahResponse>

    @GET("tafseer/{tafseer_id}/{surah_number}/{ayah_number}")
    suspend fun getTafseer(
        @Path("tafseer_id") tafseerId: Int,
        @Path("surah_number") surah: Int,
        @Path("ayah_number") ayah: Int,
    ): Response<TafseerResponse>
}

sealed class QuranResult
data class QuranSuccess(val surah: String) : QuranResult()
data class QuranError(val message: String) : QuranResult()
data class QuranHttpException(val message: String) : QuranResult()
data class InternetException(val message: String) : QuranResult()