package com.example.wzker.di

import com.example.wzker.api.QuranService
import com.example.wzker.util.Quran_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QuranProvidersDI {
    @Singleton
    @Provides
    @Named("Quran")
    fun provideQuranRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(Quran_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideQuranApiService(@Named("Quran") retrofit: Retrofit): QuranService {
        return retrofit.create(QuranService::class.java)
    }
}