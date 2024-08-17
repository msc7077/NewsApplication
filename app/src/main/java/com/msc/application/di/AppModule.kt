package com.msc.application.di

import android.app.Application
import androidx.room.Room
import com.msc.application.data.local.NewsDao
import com.msc.application.data.local.NewsDatabase
import com.msc.application.data.local.NewsTypeConvertor
import com.msc.application.data.manager.LocalUserManagerImpl
import com.msc.application.data.remote.NewsApi
import com.msc.application.data.remote.UnsplashApi
import com.msc.application.data.repository.NewsRepositoryImpl
import com.msc.application.domain.manager.LocalUserManager
import com.msc.application.domain.repository.NewsRepository
import com.msc.application.domain.usecases.app_entry.AppEntryUseCases
import com.msc.application.domain.usecases.app_entry.ReadAppEntry
import com.msc.application.domain.usecases.app_entry.SaveAppEntry
import com.msc.application.domain.usecases.news.DeleteArticle
import com.msc.application.domain.usecases.news.GetNews
import com.msc.application.domain.usecases.news.NewsUseCases
import com.msc.application.domain.usecases.news.SearchNews
import com.msc.application.domain.usecases.news.SelectArticle
import com.msc.application.domain.usecases.news.SelectArticles
import com.msc.application.domain.usecases.news.UpsertArticle
import com.msc.application.util.Constants.NEWS_BASE_URL
import com.msc.application.util.Constants.NEWS_DATABASE_NAME
import com.msc.application.util.Constants.UNSPLASH_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/***
 *
 * 종속성 주입이란 = 종속성 또는 개체를 클래스에 보내는 방법
 *
 *
 * Module : Dagger Hilt 가 제공하려는 종속성을 정의한다.
 */
@Module
@InstallIn(SingletonComponent::class) // 앱이 살아있는 한 계속 유지되로록 설정한다는 의미
object AppModule {

    /***
     * Provides 어노테이션 : 종속성 제공
     */

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

    /***
     * LocalUserManager 제공
     *
     */
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
        /***
         * Application Class 에 대한 접근 권한을 Dagger Hilt 에 부여하였기 때문에 인스턴스를 가져와 함수에 주입이 가능해진것이다.
         */
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        /***
         * provideLocalUserManager 를 Provide 하고 있기 때문에 주입이 가능하다.
         * !!! 만약 Provide 하지 않은 객체를 주입한다면 Hilt Provide 에러가 발생한다.
         */
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            selectArticles = SelectArticles(newsDao),
            selectArticle = SelectArticle(newsDao)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao {
        return newsDatabase.newsDao
    }

}