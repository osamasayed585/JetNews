package com.droidos.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.droidos.common.Language
import com.droidos.common.di.DispatcherProvider
import com.droidos.common.utils.Constants
import com.droidos.common.utils.Constants.AL_JAZEERA_NET
import com.droidos.datastore.LocalDataStore
import com.droidos.home.data.model.beans.Article
import com.droidos.home.data.remote.ArticlesService
import com.droidos.home.domain.repository.ArticlesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
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
    override fun requestArticles(query: String): Flow<PagingData<Article>> {
        val language = runBlocking {
            preferences.requestLanguage().firstOrNull() ?: Language.Arabic.value
        }
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ArticleDataSource(
                    apiService = apiService,
                    query = query,
                    language = language,
                    dispatcherProvider = dispatcherProvider
                )
            },
        ).flow
            .map { pagingData ->
                pagingData.map { article ->
                    CoroutineScope(dispatcherProvider.io).launch {
                        // articleDao.insert(article)
                    }
                    article
                }
            }
    }
}
