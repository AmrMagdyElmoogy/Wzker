package com.example.wzker.db

sealed class ZekrResult
data class ZekrSuccessFromDB(val askar: List<ZekrModel>) : ZekrResult()
data class ZekrErrorFromDB(val message: String) : ZekrResult()