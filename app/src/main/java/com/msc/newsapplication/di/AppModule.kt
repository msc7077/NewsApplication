package com.msc.newsapplication.di

import com.msc.newsapplication.data.remote.UnsplashApi
import com.msc.newsapplication.util.Constants.NEWS_BASE_URL
import com.msc.newsapplication.util.Constants.UNSPLASH_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @UnsplashRetrofit
    @Provides
    @Singleton
    fun provideUnsplashRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(UNSPLASH_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @NewsRetrofit
    @Provides
    @Singleton
    fun provideNewsRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(NEWS_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideUnsplashApi(@UnsplashRetrofit retrofit: Retrofit): UnsplashApi =
        retrofit.create(UnsplashApi::class.java)

}