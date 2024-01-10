package com.example.wzker.quran

import android.util.Log
import com.example.wzker.api.InternetException
import com.example.wzker.api.QuranApi
import com.example.wzker.api.QuranError
import com.example.wzker.api.QuranHttpException
import com.example.wzker.api.QuranResult
import com.example.wzker.api.QuranService
import com.example.wzker.api.QuranSuccess
import retrofit2.HttpException
import java.io.IOException


const val TAGs = "QuranRepo"

class QuranRepository private constructor(
    private val api: QuranService
) {
    companion object {
        private var instance: QuranRepository? = null

        fun initialize() {
            if (instance == null) {
                instance = QuranRepository(QuranApi.api)
            }
        }

        fun getInstance(): QuranRepository =
            instance ?: throw Throwable("You cannot get a nullable repostiory")
    }

    suspend fun getAyahFromAPI(surah: Int, ayah: Int): QuranResult {
        return try {
            val result = api.getAyah(surah, ayah)
            if (result.isSuccessful) {
                val data = result.body()!!.text
                Log.d(TAGs, data)
                QuranSuccess(data)
            } else {
                Log.d(TAGs, result.errorBody().toString())
                QuranError(result.errorBody().toString())
            }
        } catch (e: HttpException) {
            Log.d(TAGs, e.toString())
            QuranHttpException(e.toString())
        } catch (e: IOException) {
            Log.d(TAGs, e.toString())
            InternetException("No internet connection")
        }
    }

    suspend fun getTafseerOfAyahFromAPI(tafseer_id: Int, surah: Int, ayah: Int): QuranResult {
        return try {
            val result = api.getTafseer(tafseer_id, surah, ayah)
            if (result.isSuccessful) {
                val data = result.body()!!.text
                QuranSuccess(data)
            } else {
                QuranError(result.errorBody().toString())
            }
        } catch (e: HttpException) {
            QuranHttpException(e.toString())
        }
    }
}