package com.example.wzker.di

import android.content.Context
import androidx.room.Room
import com.example.wzker.db.ZekrDao
import com.example.wzker.db.ZekrDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseProvidersDI {
    @Singleton
    @Provides
    fun provideDatabaseObject(
        @ApplicationContext context: Context
    ): ZekrDatabase {
        return Room.databaseBuilder(context, ZekrDatabase::class.java, "AskarDatabase")
            .addCallback(ZekrDatabase.callbacks).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideDao(
        db: ZekrDatabase
    ): ZekrDao {
        return db.dao
    }
}

