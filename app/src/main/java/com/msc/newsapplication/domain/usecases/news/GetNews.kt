package com.msc.newsapplication.domain.usecases.news

import androidx.paging.PagingData
import com.msc.newsapplication.domain.model.Article
import com.msc.newsapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow


class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}