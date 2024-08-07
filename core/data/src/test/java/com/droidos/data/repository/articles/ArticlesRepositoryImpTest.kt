package com.droidos.data.repository.articles

import androidx.paging.PagingData
import com.droidos.common.di.DispatcherProvider
import com.droidos.common.utils.Constants
import com.droidos.data.remote.ArticlesService
import com.droidos.datastore.LocalDataStore
import com.droidos.model.beans.ArticleUIModel
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ArticlesRepositoryImpTest {

    private val mockApiService: ArticlesService = mockk()
    private val mockPreferences: LocalDataStore = mockk()
    private val mockDispatcherProvider: DispatcherProvider = mockk()

    @Test
    fun `Given a query, When requestArticles is called, Then it should return a flow of PagingData`() = runTest {

        val repo = ArticlesRepositoryImp(
            apiService = mockApiService,
            preferences = mockPreferences,
            dispatcherProvider = mockDispatcherProvider
        )

        val result: Flow<PagingData<ArticleUIModel>> = repo.requestArticles(Constants.ALL)

    }

}