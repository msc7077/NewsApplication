package com.msc.newsapplication.domain.usecases.news

import com.msc.newsapplication.data.local.NewsDao
import com.msc.newsapplication.domain.model.Article

class UpsertArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article) {
        newsDao.upsert(article)
    }

}