package com.droidos.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.droidos.common.Language
import com.droidos.common.utils.Constants
import com.droidos.datastore.LocalDataStore
import com.droidos.home.data.model.beans.Article
import com.droidos.home.data.remote.ArticlesService
import com.droidos.home.domain.repository.ArticlesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ArticlesRepositoryImp @Inject constructor(
    private val apiService: ArticlesService,
    private val preferences: LocalDataStore,
    private val defaultDispatcher: CoroutineDispatcher,
) : ArticlesRepository {

    override fun requestArticles(query: String): Flow<PagingData<Article>> {
        val language = runBlocking {
            preferences.requestLanguage().firstOrNull() ?: Language.Arabic.value
        }
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
            ),
            pagingSourceFactory = {
                ArticleDataSource(
                    apiService = apiService,
                    query = query,
                    language = language,
                    defaultDispatcher = defaultDispatcher
                )
            },
        ).flow
    }
}
