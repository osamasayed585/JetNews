package com.droidos.data.repository.articles

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import com.droidos.common.Language
import com.droidos.common.di.DispatcherProvider
import com.droidos.common.utils.Constants
import com.droidos.data.mapper.asExternalUiModel
import com.droidos.data.remote.ArticlesService
import com.droidos.datastore.LocalDataStore
import com.droidos.domain.repository.ArticlesRepository
import com.droidos.model.beans.ArticleUIModel
import com.droidos.model.beans.NetworkArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ArticlesRepositoryImp @Inject constructor(
    private val apiService: ArticlesService,
    private val preferences: LocalDataStore,
    private val dispatcherProvider: DispatcherProvider,
) : ArticlesRepository {

    /**
     * Requests articles based on the provided query.
     *
     * @param query The search query for articles.
     * @return A Flow of PagingData containing the articles matching the query.
     */
    override fun requestArticles(query: String): Flow<PagingData<ArticleUIModel>> {
        val language = runBlocking {
            preferences.requestLanguage().firstOrNull() ?: Language.Arabic.value
        }
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                ArticleDataSource(
                    apiService = apiService,
                    query = query,
                    language = language,
                    dispatcherProvider = dispatcherProvider
                )
            },
        )
            .flow
            .map { pagingData ->
                pagingData.insertSeparators { before: NetworkArticle?, after: NetworkArticle? ->

                }
                pagingData.map { networkArticle ->
                    networkArticle.asExternalUiModel()
                }
            }
    }
}
