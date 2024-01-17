package com.example.wzker.hadith

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wzker.util.Endpoints
import com.example.wzker.util.ErrorNotException
import com.example.wzker.util.IOException
import com.example.wzker.util.Initialization
import com.example.wzker.util.Loading
import com.example.wzker.util.ResultStates
import com.example.wzker.util.RetrofitException
import com.example.wzker.util.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HadithViewModel @Inject constructor(
    private val repo: HadithRepository
) : ViewModel() {
    private val _hadithUiState = MutableStateFlow(HadithUiState(Initialization))
    val hadithUiState = _hadithUiState.asStateFlow()
    private var hadithNumber: Int = 1
    var currentSelectedBook = ""

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getHadith(endpoint: String) {
        currentSelectedBook = endpoint
        viewModelScope.launch {
            val apiEndpoint: String = selectProperEndPoint(endpoint)

            withContext(Dispatchers.IO) {
                _hadithUiState.update {
                    it.copy(state = Loading)
                }
                when (val result = repo.getRandomHadithBasedOnCategory(apiEndpoint, hadithNumber)) {
                    is ErrorNotException -> {
                        _hadithUiState.update {
                            it.copy(state = ErrorNotException(result.message))
                        }
                    }

                    is IOException -> {
                        _hadithUiState.update {
                            it.copy(state = IOException(result.message))
                        }
                    }

                    is Success<*> -> {
                        _hadithUiState.update {
                            it.copy(state = Success(result.data))
                        }
                    }

                    is RetrofitException -> {
                        _hadithUiState.update {
                            it.copy(state = RetrofitException(result.message))
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    private fun selectProperEndPoint(endpoint: String) = when (endpoint) {
        Endpoints.MUSLIM.name -> {
            hadithNumber = getRandomNumberOfHadith(Endpoints.MUSLIM.upperLimit)
            Endpoints.MUSLIM.endpoint
        }

        Endpoints.BUKHARI.name -> {
            hadithNumber = getRandomNumberOfHadith(Endpoints.BUKHARI.upperLimit)

            Endpoints.BUKHARI.endpoint
        }

        Endpoints.TIRMIDZI.name -> {
            hadithNumber = getRandomNumberOfHadith(Endpoints.TIRMIDZI.upperLimit)
            Endpoints.TIRMIDZI.endpoint
        }

        Endpoints.NASAI.name -> {
            hadithNumber = getRandomNumberOfHadith(Endpoints.NASAI.upperLimit)
            Endpoints.NASAI.endpoint
        }

        Endpoints.ABU_DAUD.name -> {
            hadithNumber = getRandomNumberOfHadith(Endpoints.ABU_DAUD.upperLimit)
            Endpoints.ABU_DAUD.endpoint
        }

        Endpoints.IBNU_MAJAH.name -> {
            hadithNumber = getRandomNumberOfHadith(Endpoints.IBNU_MAJAH.upperLimit)
            Endpoints.IBNU_MAJAH.endpoint
        }

        Endpoints.AHMAD.name -> {
            hadithNumber = getRandomNumberOfHadith(Endpoints.AHMAD.upperLimit)
            Endpoints.AHMAD.endpoint
        }

        Endpoints.MALIK.name -> {
            hadithNumber = getRandomNumberOfHadith(Endpoints.MALIK.upperLimit)
            Endpoints.MALIK.endpoint
        }

        else -> {
            ""
        }
    }

    private fun getRandomNumberOfHadith(upperLimit: Int) = Random.nextInt(upperLimit)
}


data class HadithUiState(
    val state: ResultStates
)
