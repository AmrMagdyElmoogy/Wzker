package com.example.wzker.azkar

import android.util.Log
import androidx.room.Room
import com.example.wzker.db.ZekrDao
import com.example.wzker.db.ZekrDatabase
import kotlinx.coroutines.flow.Flow
import com.example.wzker.db.ZekrModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class AzkarRepository() {
    private val db = ZekrDatabase.getInstance()

    fun getAllAzkar(): Flow<List<ZekrModel>> {
        return try {
            db.dao.getAllAzkar()
        } catch (e: Exception) {
            Log.d("DB", e.toString())
            return flowOf(emptyList())
        }
    }

    suspend fun insertNewZekr(zekrModel: ZekrModel) {
        try {
            db.dao.insertNewZekr(zekrModel)
        } catch (e: Exception) {
            Log.d("DB", e.toString())
        }
    }

    suspend fun updateNewZekr(newZekr: ZekrModel) {
        try {
            db.dao.updateZekr(newZekr)
        } catch (e: Exception) {
            Log.d("DB", e.toString())
        }
    }

}