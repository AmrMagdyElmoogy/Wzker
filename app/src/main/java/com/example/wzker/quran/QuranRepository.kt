package com.example.wzker.quran

import android.util.Log
import com.example.wzker.R
import com.example.wzker.api.QuranService
import com.example.wzker.util.ErrorNotException
import com.example.wzker.util.ResultStates
import com.example.wzker.util.RetrofitException
import com.example.wzker.util.Success
import com.example.wzker.util.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton


const val TAGs = "QuranRepo"

@Singleton
class QuranRepository @Inject constructor(
    private val api: QuranService
) {

    suspend fun getAyahFromAPI(surah: Int, ayah: Int): ResultStates {
        return try {
            val result = api.getAyah(surah, ayah)
            if (result.isSuccessful) {
                val data = result.body()!!.text
                Log.d(TAGs, data)
                Success(data)
            } else {
                Log.d(TAGs, result.errorBody().toString())
                ErrorNotException(R.string.errorHappend)
            }
        } catch (e: HttpException) {
            Log.d(TAGs, e.toString())
            RetrofitException(R.string.noInternet)
        } catch (e: java.io.IOException) {
            Log.d(TAGs, e.toString())
            IOException(R.string.noInternet)
        } catch (e: SocketTimeoutException) {
            IOException(R.string.noInternet)
        }
    }

    suspend fun getTafseerOfAyahFromAPI(tafseer_id: Int, surah: Int, ayah: Int): ResultStates {
        return try {
            val result = api.getTafseer(tafseer_id, surah, ayah)
            if (result.isSuccessful) {
                val data = result.body()!!.text
                Success(data)
            } else {
                ErrorNotException(R.string.errorHappend)
            }
        } catch (e: HttpException) {
            Log.d(TAGs, e.toString())
            RetrofitException(R.string.noInternet)
        } catch (e: java.io.IOException) {
            Log.d(TAGs, e.toString())
            IOException(R.string.noInternet)
        } catch (e: SocketTimeoutException) {
            IOException(R.string.noInternet)
        }
    }
}