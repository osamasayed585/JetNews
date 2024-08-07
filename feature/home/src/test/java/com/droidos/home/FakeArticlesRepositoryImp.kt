package com.droidos.home

import androidx.paging.PagingData
import com.droidos.common.utils.Constants
import com.droidos.domain.repository.ArticlesRepository
import com.droidos.model.beans.ArticleUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeArticlesRepositoryImp : ArticlesRepository {

    override fun requestArticles(query: String): Flow<PagingData<ArticleUIModel>> {
        return when(query){
            Constants.ALL -> flowOf(PagingData.from(dummySuccess_HomeState))
            Constants.NEW_QUERY -> flowOf(PagingData.from(changes_dummySuccess_HomeState))
            Constants.REMOVE_QUERY -> flowOf(PagingData.from(changes_dummySuccess_HomeState))
            Constants.THROW_ERROR -> flowOf(PagingData.empty())
            else -> flowOf(PagingData.empty())

        }
    }
}