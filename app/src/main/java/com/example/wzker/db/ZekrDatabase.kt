package com.example.wzker.db

import android.content.ContentValues
import android.content.Context
import androidx.room.Database
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.wzker.R

@Database(entities = [ZekrModel::class], version = 1)
abstract class ZekrDatabase : RoomDatabase() {
    companion object {
        private var instance: ZekrDatabase? = null
        fun initialize(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, ZekrDatabase::class.java, "AskarDatabase")
                    .addCallback(callbacks).fallbackToDestructiveMigration().build()
            }
        }

        fun getInstance(): ZekrDatabase =
            instance ?: throw (Throwable("Database isn't initialized yet!"))
    }

    object callbacks : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val zekrList = listOf(
                ZekrModel(
                    id = 1,
                    primaryZekar = R.string.azkar_1,
                    description = R.string.azkar_1_des,
                    allCount = 0,

                    ),
                ZekrModel(
                    id = 2,
                    primaryZekar = R.string.azkar_2,
                    description = R.string.azkar_2_des,
                    allCount = 0,

                    ),
                ZekrModel(
                    id = 3,
                    primaryZekar = R.string.azkar_3,
                    description = R.string.azkar_3_des,
                    allCount = 0,

                    ),
                ZekrModel(
                    id = 4,
                    primaryZekar = R.string.azkar_4,
                    description = R.string.azkar_4_des,
                    allCount = 0,

                    ),
                ZekrModel(
                    id = 5,
                    primaryZekar = R.string.azkar_5,
                    description = R.string.azkar_5_des,
                    allCount = 0,

                    ),
            )

            zekrList.forEach {
                val contentValues = ContentValues().apply {
                    put("id", it.id)
                    put("primaryZekar", it.primaryZekar)
                    put("description", it.description)
                    put("allCount", it.allCount)
                }
                db.insert(
                    "Azkar",
                    conflictAlgorithm = OnConflictStrategy.IGNORE,
                    values = ContentValues(contentValues)
                )
            }
        }
    }

    abstract val dao: ZekrDao
}