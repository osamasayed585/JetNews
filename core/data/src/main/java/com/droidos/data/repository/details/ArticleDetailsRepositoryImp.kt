package com.droidos.data.repository.details

import com.droidos.common.Language
import com.droidos.common.di.DispatcherProvider
import com.droidos.data.mapper.asExternalUiModel
import com.droidos.data.remote.ArticlesService
import com.droidos.datastore.LocalDataStore
import com.droidos.domain.repository.ArticleDetailsRepository
import com.droidos.model.beans.NetworkArticle
import com.droidos.network.di.errorHandler.entities.ErrorHandler
import com.droidos.network.di.errorHandler.safeApiCall
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ArticleDetailsRepositoryImp @Inject constructor(
    private val apiService: ArticlesService,
    private val preferences: LocalDataStore,
    private val dispatcherProvider: DispatcherProvider,
    private val errorHandler: ErrorHandler
) : ArticleDetailsRepository {

    private val language by lazy {
        runBlocking { preferences.requestLanguage().firstOrNull() ?: Language.Arabic.value }
    }

    override suspend fun requestArticleDetails(query: String) = safeApiCall(
        errorHandler = errorHandler,
        dispatcher = dispatcherProvider,
        apiCall = {
            apiService.requestArticleDetail(query = query, language = language)
        },
        apiResultOf = { articles: List<NetworkArticle> ->
            Result.success(articles.first().asExternalUiModel())
        }
    )
}
