package com.droidos.details

import com.droidos.common.base.RequestResult
import com.droidos.common.utils.Constants
import com.droidos.domain.repository.ArticleDetailsRepository
import com.droidos.model.beans.ArticleUIModel

class FakeArticleDetailsRepositoryImp : ArticleDetailsRepository {
    override suspend fun requestArticleDetails(query: String): RequestResult<ArticleUIModel> {
        return when(query){
            Constants.ALL -> RequestResult.Success(defaultDetailArticleUi)
            SPECIFIC_TITLE -> RequestResult.Success(specificTitleArticleUi)
            else -> RequestResult.Error(ERROR_MESSAGE)
        }
    }
}