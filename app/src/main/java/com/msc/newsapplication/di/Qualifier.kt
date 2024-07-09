package com.msc.newsapplication.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UnsplashRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsRetrofit
