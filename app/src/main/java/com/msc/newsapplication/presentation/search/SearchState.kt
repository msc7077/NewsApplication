package com.msc.newsapplication.presentation.search

import androidx.paging.PagingData
import com.msc.newsapplication.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
) {
}