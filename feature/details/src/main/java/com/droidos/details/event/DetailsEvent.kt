package com.droidos.details.event

import com.droidos.common.base.ScreenEvent
import com.droidos.model.beans.ArticleUIModel

sealed class DetailsEvent : ScreenEvent {
    data class OnGetArticleDetails(val article: ArticleUIModel) : DetailsEvent()
    data class OnGetError(val message: String) : DetailsEvent()
}
