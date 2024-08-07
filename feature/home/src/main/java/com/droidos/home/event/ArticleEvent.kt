package com.droidos.home.event

import androidx.paging.PagingData
import com.droidos.common.base.ScreenEvent
import com.droidos.model.beans.ArticleUIModel


sealed class ArticleEvent: ScreenEvent {
    data class ShowArticles(val articles: PagingData<ArticleUIModel>) : ArticleEvent()
}