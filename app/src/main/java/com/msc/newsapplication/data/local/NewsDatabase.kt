package com.msc.newsapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.msc.newsapplication.domain.model.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(NewsTypeConvertor::class)
abstract class NewsDatabase: RoomDatabase() {

    abstract val newsDao: NewsDao


}