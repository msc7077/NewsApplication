package com.msc.application.presentation.details

import com.msc.application.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDetailArticle(val article: Article): DetailsEvent()

    object RemoveSideEffect: DetailsEvent()

}