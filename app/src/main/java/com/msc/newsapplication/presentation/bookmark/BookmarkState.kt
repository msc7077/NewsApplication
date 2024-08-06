package com.msc.newsapplication.presentation.bookmark

import com.msc.newsapplication.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)