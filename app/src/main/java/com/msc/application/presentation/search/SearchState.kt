package com.msc.application.presentation.search

import androidx.paging.PagingData
import com.msc.application.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
) {
}