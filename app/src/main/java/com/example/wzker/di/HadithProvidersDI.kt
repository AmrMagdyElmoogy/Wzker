package com.example.wzker.di

import com.example.wzker.api.HadithService
import com.example.wzker.util.baseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HadithProvidersDI {

    @Singleton
    @Provides
    @Named("Hadith")
    fun provideHadithRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideHadithApiService(@Named("Hadith") retrofit: Retrofit): HadithService {
        return retrofit.create(HadithService::class.java)
    }

}

