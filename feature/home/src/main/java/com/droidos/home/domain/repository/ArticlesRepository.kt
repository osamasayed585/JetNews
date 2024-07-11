package com.droidos.home.domain.repository

import androidx.paging.PagingData
import com.droidos.home.data.model.beans.Article
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {

    fun requestArticles(query: String): Flow<PagingData<Article>>
}