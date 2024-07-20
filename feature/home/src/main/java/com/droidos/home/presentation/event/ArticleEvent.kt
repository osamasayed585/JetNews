package com.droidos.home.presentation.event

import androidx.paging.PagingData
import com.droidos.common.base.ScreenEvent
import com.droidos.home.domain.model.ArticleUIModel

sealed class ArticleEvent: ScreenEvent {
    data class ShowArticles(val articles: PagingData<ArticleUIModel>) : ArticleEvent()
}