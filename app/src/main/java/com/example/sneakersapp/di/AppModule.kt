package com.example.sneakersapp.di

import com.example.sneakersapp.api.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Prashant Verma
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit) : APIService =
        retrofit.create(APIService::class.java)

}