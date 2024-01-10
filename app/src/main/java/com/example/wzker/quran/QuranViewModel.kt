package com.example.wzker.quran

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wzker.api.InternetException
import com.example.wzker.api.QuranError
import com.example.wzker.api.QuranHttpException
import com.example.wzker.api.QuranResult
import com.example.wzker.api.QuranSuccess
import com.example.wzker.util.quranSurahsArabicWithAyatCount
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class QuranViewModel : ViewModel() {
    private val _quranUiState = MutableStateFlow(QuranUiState(QuranInitializationState))
    val quranUiState = _quranUiState.asStateFlow()
    private val repo: QuranRepository = QuranRepository.getInstance()

    init {
        val surah = getRandomSurah()
        val ayah = getRandomAyah(surah)
        getAyah(surah, ayah)
    }

    private fun getAyah(surah: Int, ayah: Int) {
        _quranUiState.update {
            it.copy(state = QuranLoadingState)
        }

        viewModelScope.launch {
            val result: QuranResult
            withContext(Dispatchers.IO) {
                result = repo.getAyahFromAPI(surah, ayah)
            }
            when (result) {
                is QuranError -> {
                    _quranUiState.update {
                        it.copy(state = QuranErrorState(result.message))
                    }
                }

                is QuranHttpException -> {
                    _quranUiState.update {
                        it.copy(state = QuranExceptionState(result.message))
                    }
                }

                is QuranSuccess -> {
                    _quranUiState.update {
                        it.copy(
                            state = QuranSuccessState(
                                surah = result.surah,
                                surah_name = getSurahNameBasedOnSurahNumber(surah),
                                tafseer = getTafseerOfAyah(1, surah, ayah).await(),
                                surah_number = ayah
                            )
                        )
                    }
                }

                is InternetException -> {
                    _quranUiState.update {
                        it.copy(state = QuranInternetLost)
                    }
                }
            }
        }
    }

    private fun getTafseerOfAyah(tafseerId: Int, surah: Int, ayah: Int): Deferred<String> {
        return viewModelScope.async {
            val result: QuranResult
            withContext(Dispatchers.IO) {
                result = repo.getTafseerOfAyahFromAPI(tafseerId, surah, ayah)
            }
            if (result is QuranHttpException) {
                _quranUiState.update {
                    it.copy(state = QuranExceptionState(result.message))
                }
            }
            val state = result as QuranSuccess
            state.surah
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
    val state: QuranStates
)

sealed class QuranStates
data object QuranLoadingState : QuranStates()
data object QuranInitializationState : QuranStates()
data object QuranInternetLost : QuranStates()
data class QuranSuccessState(
    val surah: String,
    val tafseer: String,
    val surah_name: String,
    val surah_number: Int
) :
    QuranStates()

data class QuranExceptionState(val message: String) : QuranStates()
data class QuranErrorState(val message: String) : QuranStates()