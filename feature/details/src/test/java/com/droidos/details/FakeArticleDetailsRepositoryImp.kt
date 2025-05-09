package com.droidos.details

import com.droidos.common.utils.Constants
import com.droidos.domain.repository.ArticleDetailsRepository
import com.droidos.model.beans.ArticleUIModel

class FakeArticleDetailsRepositoryImp : ArticleDetailsRepository {
    override suspend fun requestArticleDetails(query: String): Result<ArticleUIModel> {
        return when(query){
            Constants.ALL -> Result.success(defaultDetailArticleUi)
            SPECIFIC_TITLE -> Result.success(specificTitleArticleUi)
            else -> Result.failure(IllegalStateException(ERROR_MESSAGE))
        }
    }
}