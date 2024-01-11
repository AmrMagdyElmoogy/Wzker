package com.example.wzker.util

import androidx.annotation.StringRes

sealed interface ResultStates
data object Initialization : ResultStates
data object Loading : ResultStates
data class Success<T>(val data: T) : ResultStates
data class ErrorNotException(@StringRes val message: Int) : ResultStates
data class IOException(@StringRes val message: Int) : ResultStates
data class RetrofitException(@StringRes val message: Int) : ResultStates

