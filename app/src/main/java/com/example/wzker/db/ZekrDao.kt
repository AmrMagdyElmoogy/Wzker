package com.example.wzker.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ZekrDao {

    @Query("select * from Azkar")
    fun getAllAzkar(): Flow<List<ZekrModel>>

    @Insert(entity = ZekrModel::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewZekr(zekrModel: ZekrModel)

    @Update(entity = ZekrModel::class, onConflict = OnConflictStrategy.ABORT)
    suspend fun updateZekr(newZekr: ZekrModel)
}