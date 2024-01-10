package com.example.wzker.hadith

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wzker.api.HadithError
import com.example.wzker.api.HadithException
import com.example.wzker.api.HadithResult
import com.example.wzker.api.HadithSuccess
import com.example.wzker.util.Endpoints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class HadithViewModel : ViewModel() {
    private val _hadithUiState = MutableStateFlow(HadithUiState(HadithInitializationState))
    val hadithUiState = _hadithUiState.asStateFlow()
    private val repo: HadithRepository = HadithRepository.getInstance()
    private var hadithNumber: Int = 1
    var currentSelectedBook = ""
    fun getHadith(endpoint: String) {
        currentSelectedBook = endpoint
        viewModelScope.launch {
            val end: String = when (endpoint) {
                Endpoints.MUSLIM.name -> {
                    hadithNumber = Random.nextInt(Endpoints.MUSLIM.upperLimit)
                    Endpoints.MUSLIM.endpoint
                }

                Endpoints.BUKHARI.name -> {
                    hadithNumber = Random.nextInt(Endpoints.BUKHARI.upperLimit)

                    Endpoints.BUKHARI.endpoint
                }

                Endpoints.TIRMIDZI.name -> {
                    hadithNumber = Random.nextInt(Endpoints.TIRMIDZI.upperLimit)
                    Endpoints.TIRMIDZI.endpoint
                }

                Endpoints.NASAI.name -> {
                    hadithNumber = Random.nextInt(Endpoints.NASAI.upperLimit)
                    Endpoints.NASAI.endpoint
                }

                Endpoints.ABU_DAUD.name -> {
                    hadithNumber = Random.nextInt(Endpoints.ABU_DAUD.upperLimit)
                    Endpoints.ABU_DAUD.endpoint
                }

                Endpoints.IBNU_MAJAH.name -> {
                    hadithNumber = Random.nextInt(Endpoints.IBNU_MAJAH.upperLimit)
                    Endpoints.IBNU_MAJAH.endpoint
                }

                Endpoints.AHMAD.name -> {
                    hadithNumber = Random.nextInt(Endpoints.AHMAD.upperLimit)
                    Endpoints.AHMAD.endpoint
                }

                Endpoints.MALIK.name -> {
                    hadithNumber = Random.nextInt(Endpoints.MALIK.upperLimit)
                    Endpoints.MALIK.endpoint
                }

                else -> {
                    ""
                }
            }

            withContext(Dispatchers.IO) {
                _hadithUiState.update {
                    it.copy(state = HadithLoadingState)
                }
                val result = repo.getRandomHadithBasedOnCategory(end, hadithNumber)
                when (result) {
                    is HadithError -> {
                        _hadithUiState.update {
                            it.copy(state = HadithErrorState(result.message))
                        }
                    }

                    is HadithException -> {
                        _hadithUiState.update {
                            it.copy(state = HadithExceptionState(result.message))
                        }
                    }

                    is HadithSuccess -> {
                        _hadithUiState.update {
                            it.copy(state = HadithSuccessState(result.hadith))
                        }
                    }
                }
            }


        }
    }
}


data class HadithUiState(
    val state: HadithStates
)

sealed class HadithStates
data object HadithLoadingState : HadithStates()
data object HadithInitializationState : HadithStates()
data class HadithSuccessState(val hadith: String) : HadithStates()
data class HadithExceptionState(val message: String) : HadithStates()
data class HadithErrorState(val message: String) : HadithStates()
