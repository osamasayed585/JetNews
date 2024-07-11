package com.droidos.home.presentation.event

import androidx.paging.PagingData
import com.droidos.common.base.ScreenEvent
import com.droidos.home.domain.model.ArticleUIModel

sealed class ArticleEvent: ScreenEvent {

    data class ShowArticles(val articles: PagingData<ArticleUIModel>) : ArticleEvent()
//    data class Search(val query: String) : ArticleEvent()
//    data class Refresh(val query: String) : ArticleEvent()
//    data class ShowError(val error: String) : ArticleEvent()
//    data object ShowLoading : ArticleEvent()
//    data object HideLoading : ArticleEvent()
//    data object ShowEmpty : ArticleEvent()
//    data object HideEmpty : ArticleEvent()

}