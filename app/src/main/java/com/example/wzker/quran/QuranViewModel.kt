package com.example.wzker.quran

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wzker.util.ErrorNotException
import com.example.wzker.util.IOException
import com.example.wzker.util.Initialization
import com.example.wzker.util.Loading
import com.example.wzker.util.ResultStates
import com.example.wzker.util.RetrofitException
import com.example.wzker.util.Success
import com.example.wzker.util.quranSurahsArabicWithAyatCount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.xml.transform.Result
import kotlin.random.Random

@HiltViewModel
class QuranViewModel @Inject constructor(
    private val repo: QuranRepository
) : ViewModel() {
    private val _quranUiState = MutableStateFlow(QuranUiState(Initialization))
    val quranUiState = _quranUiState.asStateFlow()

    init {
        val surah = getRandomSurah()
        val ayah = getRandomAyah(surah)
        getAyah(surah, ayah)
    }

    private fun getAyah(surah: Int, ayah: Int) {
        _quranUiState.update {
            it.copy(state = Loading)
        }

        viewModelScope.launch {
            val result: ResultStates
            withContext(Dispatchers.IO) {
                result = repo.getAyahFromAPI(surah, ayah)
            }
            when (result) {
                is ErrorNotException -> {
                    _quranUiState.update {
                        it.copy(state = ErrorNotException(result.message))
                    }
                }

                is RetrofitException -> {
                    _quranUiState.update {
                        it.copy(state = RetrofitException(result.message))
                    }
                }

                is Success<*> -> {
                    _quranUiState.update {
                        it.copy(
                            state = Success(
                                Quran(
                                    surah = result.data as String,
                                    surah_name = getSurahNameBasedOnSurahNumber(surah),
                                    tafseer = getTafseerOfAyah(1, surah, ayah).await(),
                                    surah_number = ayah
                                ),
                            )
                        )
                    }
                }

                is IOException -> {
                    _quranUiState.update {
                        it.copy(state = IOException(message = result.message))
                    }
                }

                else -> {}
            }
        }
    }

    private fun getTafseerOfAyah(tafseerId: Int, surah: Int, ayah: Int): Deferred<String> {
        return viewModelScope.async {
            val result: ResultStates
            withContext(Dispatchers.IO) {
                result = repo.getTafseerOfAyahFromAPI(tafseerId, surah, ayah)
            }
            if (result is RetrofitException) {
                _quranUiState.update {
                    it.copy(state = RetrofitException(result.message))
                }
                "Empty"
            } else if (result is IOException) {
                _quranUiState.update {
                    it.copy(state = IOException(result.message))
                }
                "Empty"
            } else if (result is Success<*>) {
                result.data as String
            } else {
                "Empty"
            }

        }
    }

    fun getAnotherAyah() {
        viewModelScope.launch(Dispatchers.IO) {
            val surah = getRandomSurah()
            val ayah = getRandomAyah(surah)
            Log.d("getAnotherAyah", surah.toString())
            Log.d("getAnotherAyah", ayah.toString())
            getAyah(surah, ayah)
        }
    }


    private fun getRandomSurah() = Random.nextInt(from = 1, until = 115)
    private fun getRandomAyah(surahNumber: Int) =
        Random.nextInt(from = 1, until = quranSurahsArabicWithAyatCount[surahNumber]!!.second + 1)

    private fun getSurahNameBasedOnSurahNumber(surahNumber: Int) =
        quranSurahsArabicWithAyatCount[surahNumber]!!.first


}

data class QuranUiState(
    val state: ResultStates
)