package com.example.wzker.hadith

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.example.wzker.R
import com.example.wzker.api.HadithService
import com.example.wzker.util.ErrorNotException
import com.example.wzker.util.IOException
import com.example.wzker.util.RetrofitException
import com.example.wzker.util.ResultStates
import com.example.wzker.util.Success
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

const val TAG = "HadithRepo"

@Singleton
class HadithRepository @Inject constructor(
    private val api: HadithService
) {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun getRandomHadithBasedOnCategory(endpoint: String, number: Int): ResultStates {
        return try {
            val result = api.getRandomHadith(endpoint, number)
            if (result.isSuccessful) {
                Log.d(TAG, result.body()!!.data.toString())
                Success(data = result.body()!!.data.contents.arab)
            } else {
                Log.d(TAG, result.errorBody().toString())
                ErrorNotException(message = R.string.errorHappend)
            }
        } catch (e: HttpException) {
            RetrofitException(message = R.string.errorHappend)
        } catch (e: java.io.IOException) {
            IOException(message = R.string.noInternet)
        } catch (e: SocketTimeoutException) {
            RetrofitException(message = R.string.errorHappend)
        }
    }
}