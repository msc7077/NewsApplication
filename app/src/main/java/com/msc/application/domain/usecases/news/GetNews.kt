package com.msc.application.domain.usecases.news

import androidx.paging.PagingData
import com.msc.application.domain.model.Article
import com.msc.application.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow


class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}