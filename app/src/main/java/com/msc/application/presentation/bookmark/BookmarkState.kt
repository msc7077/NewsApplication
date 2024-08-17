package com.msc.application.presentation.bookmark

import com.msc.application.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)