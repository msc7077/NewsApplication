package com.msc.newsapplication.domain.usecases.news

import com.msc.newsapplication.data.local.NewsDao
import com.msc.newsapplication.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDao: NewsDao
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

}