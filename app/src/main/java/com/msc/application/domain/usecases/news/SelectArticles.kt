package com.msc.application.domain.usecases.news

import com.msc.application.data.local.NewsDao
import com.msc.application.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDao: NewsDao
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

}