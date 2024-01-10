package com.example.wzker.hadith

import android.util.Log
import com.example.wzker.api.HadithApi
import com.example.wzker.api.HadithError
import com.example.wzker.api.HadithException
import com.example.wzker.api.HadithResult
import com.example.wzker.api.HadithService
import com.example.wzker.api.HadithSuccess

const val TAG = "HadithRepo"
class HadithRepository private constructor(
    private val api: HadithService
) {

    companion object {
        private var instance: HadithRepository? = null
        fun initialize() {
            if (instance == null) {
                instance = HadithRepository(HadithApi.api)
            }
        }

        fun getInstance(): HadithRepository {
            return instance ?: throw (Throwable("You cannot access repo right now"))
        }
    }

    suspend fun getRandomHadithBasedOnCategory(endpoint: String, number: Int): HadithResult {
        return try {
            val result = api.getRandomHadith(endpoint, number)
            if (result.isSuccessful) {
                Log.d(TAG, result.body()!!.data.toString())
                HadithSuccess(hadith = result.body()!!.data.contents.arab)
            } else {
                Log.d(TAG, result.errorBody().toString())
                HadithError(result.errorBody().toString())
            }
        } catch (e: Exception) {
            HadithException(message = e.message.toString())
        }
    }
}