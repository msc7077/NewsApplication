package com.msc.application.domain.usecases.news

import com.msc.application.data.local.NewsDao
import com.msc.application.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(url: String): Article? {
        return newsDao.getArticle(url)
    }

}