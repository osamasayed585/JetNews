package com.droidos.domain.repository

import androidx.paging.PagingData
import com.droidos.model.beans.ArticleUIModel
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {

    fun requestArticles(query: String): Flow<PagingData<ArticleUIModel>>
}