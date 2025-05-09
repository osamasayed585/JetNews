package com.droidos.domain.repository

import com.droidos.model.beans.ArticleUIModel

interface ArticleDetailsRepository {

    suspend fun requestArticleDetails(query: String): Result<ArticleUIModel>
}