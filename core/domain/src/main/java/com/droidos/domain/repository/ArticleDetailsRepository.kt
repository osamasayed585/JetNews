package com.droidos.domain.repository

import com.droidos.common.base.RequestResult
import com.droidos.model.beans.ArticleUIModel

interface ArticleDetailsRepository {

    suspend fun requestArticleDetails(query: String): RequestResult<ArticleUIModel>
}