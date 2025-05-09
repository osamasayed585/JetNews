package com.droidos.details.event

import com.droidos.common.base.ScreenEvent
import com.droidos.model.beans.ArticleUIModel
import com.droidos.network.di.errorHandler.entities.ErrorEntity

sealed class DetailsEvent : ScreenEvent {
    data class OnGetArticleDetails(val article: ArticleUIModel) : DetailsEvent()
    data class OnGetError(val type: ErrorEntity) : DetailsEvent()
    data object ClearError : DetailsEvent()
}
