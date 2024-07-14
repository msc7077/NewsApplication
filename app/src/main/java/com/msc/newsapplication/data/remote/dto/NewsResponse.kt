package com.msc.newsapplication.data.remote.dto

import com.msc.newsapplication.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)