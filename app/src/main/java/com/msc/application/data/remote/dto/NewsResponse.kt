package com.msc.application.data.remote.dto

import com.msc.application.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)