package com.msc.application.domain.usecases.news

import com.msc.application.data.local.NewsDao
import com.msc.application.domain.model.Article

class DeleteArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article) {
        newsDao.delete(article)
    }

}