package com.droidos.data.repository.articles

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.droidos.common.di.DispatcherProvider
import com.droidos.common.utils.Constants.INITIAL_PAGE
import com.droidos.data.remote.ArticlesService
import com.droidos.model.beans.NetworkArticle
import com.droidos.network.di.errorHandler.entities.ErrorHandler
import com.droidos.network.di.errorHandler.safeApiCall

class ArticleDataSource(
    private val apiService: ArticlesService,
    private val errorHandler: ErrorHandler,
    private val language: String,
    private val query: String,
    private val dispatcherProvider: DispatcherProvider,
) : PagingSource<Int, NetworkArticle>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkArticle> {
        val page = params.key ?: INITIAL_PAGE
        return safeApiCall(
            errorHandler = errorHandler,
            dispatcher = dispatcherProvider,
            apiCall = {
                apiService.requestArticles(
                    query = query,
                    language = language,
                    page = page
                )
            },
            apiResultOf = { articles ->
                Result.success(articles)
            }
        ).fold(
            onSuccess = { articles ->
                LoadResult.Page(
                    data = articles,
                    prevKey = if (page == INITIAL_PAGE) null else page.minus(1),
                    nextKey = if (articles.isEmpty()) null else page.plus(1),
                )
            },
            onFailure = { it: Throwable ->
                LoadResult.Error(Exception(it))
            }
        )
    }

    override fun getRefreshKey(state: PagingState<Int, NetworkArticle>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}