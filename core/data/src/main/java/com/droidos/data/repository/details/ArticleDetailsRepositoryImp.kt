package com.droidos.data.repository.details

import com.droidos.common.Language
import com.droidos.common.base.RequestResult
import com.droidos.common.di.DispatcherProvider
import com.droidos.common.utils.Constants
import com.droidos.data.mapper.asExternalUiModel
import com.droidos.data.remote.ArticlesService
import com.droidos.datastore.LocalDataStore
import com.droidos.domain.repository.ArticleDetailsRepository
import com.droidos.model.beans.ArticleUIModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ArticleDetailsRepositoryImp @Inject constructor(
    private val apiService: ArticlesService,
    private val preferences: LocalDataStore,
    private val dispatcherProvider: DispatcherProvider,
) : ArticleDetailsRepository {


    override suspend fun requestArticleDetails(query: String): RequestResult<ArticleUIModel> {
        val language = runBlocking { preferences.requestLanguage().firstOrNull() ?: Language.Arabic.value }

        return try {
            val response = apiService.requestArticles(
                query = query,
                language = language
            )
            RequestResult.Success(response.articles.first().asExternalUiModel())
        } catch (ex: Exception) {
            ex.printStackTrace()
            RequestResult.Error(ex.localizedMessage ?: Constants.UNKNOWN_ERROR)
        }
    }
}
