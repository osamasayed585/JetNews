package com.droidos.home

import androidx.paging.PagingData
import androidx.paging.map
import app.cash.turbine.test
import com.droidos.common.di.DefaultDispatcherProvider
import com.droidos.common.utils.Constants
import com.droidos.domain.useCases.GetArticlesUseCase
import com.droidos.model.beans.ArticleUIModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Rule
import org.junit.Test


class ArticleViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    @Test
    fun testRequestArticles_EmptyData() = runTest {
        val dispatcherProvider = DefaultDispatcherProvider()
        val repo = FakeArticlesRepositoryImp()
        val useCase = GetArticlesUseCase(repo)
        val viewModel = ArticleViewModel(useCase, dispatcherProvider)

        viewModel.requestArticles(Constants.EMPTY_QUERY)

        viewModel.articles.test {
            val emittedList = awaitItem().collectData()
            val expectedList = PagingData.empty<ArticleUIModel>().collectData()

            expectedList shouldBeEqualTo emittedList
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun testRequestArticles_ValidData() = runTest {
        val dispatcherProvider = DefaultDispatcherProvider()
        val repo = FakeArticlesRepositoryImp()
        val useCase = GetArticlesUseCase(repo)
        val viewModel = ArticleViewModel(useCase, dispatcherProvider)

        viewModel.requestArticles(Constants.ALL)

        viewModel.articles.test {
            val emittedList = awaitItem().collectData()
            val expectedList = PagingData.from(dummySuccess_HomeState).collectData()

            expectedList shouldBeEqualTo emittedList
            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun testRequestArticles_DataChange() = runTest {
        val dispatcherProvider = DefaultDispatcherProvider()
        val repo = FakeArticlesRepositoryImp()
        val useCase = GetArticlesUseCase(repo)
        val viewModel = ArticleViewModel(useCase, dispatcherProvider)

        viewModel.requestArticles(Constants.ALL)

        viewModel.articles.test {
            val expectedList = PagingData.from(dummySuccess_HomeState).collectData()
            awaitItem().collectData() shouldBeEqualTo expectedList

            viewModel.requestArticles(Constants.NEW_QUERY)

            val expectedListAfterChanges =
                PagingData.from(changes_dummySuccess_HomeState).collectData()

            awaitItem().collectData() shouldBeEqualTo expectedListAfterChanges

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun testRequestArticles_DataRemoval() = runTest {
        val dispatcherProvider = DefaultDispatcherProvider()
        val repo = FakeArticlesRepositoryImp()
        val useCase = GetArticlesUseCase(repo)
        val viewModel = ArticleViewModel(useCase, dispatcherProvider)

        viewModel.requestArticles(Constants.ALL)

        viewModel.articles.test {
            val expectedList = PagingData.from(dummySuccess_HomeState).collectData()
            awaitItem().collectData() shouldBeEqualTo expectedList

            viewModel.requestArticles(Constants.REMOVE_QUERY)

            val expectedAfterRemoval =
                PagingData.from(remove_dummySuccess_HomeState).collectData()

            awaitItem().collectData() shouldBeEqualTo expectedAfterRemoval

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun testRequestArticles_DataInsertion() = runTest {
        val dispatcherProvider = DefaultDispatcherProvider()
        val repo = FakeArticlesRepositoryImp()
        val useCase = GetArticlesUseCase(repo)
        val viewModel = ArticleViewModel(useCase, dispatcherProvider)

        viewModel.requestArticles(Constants.ALL)

        viewModel.articles.test {
            val expectedList = PagingData.from(dummySuccess_HomeState).collectData()
            awaitItem().collectData() shouldBeEqualTo expectedList

            viewModel.requestArticles(Constants.INSERT_QUERY)

            val expectedListAfterInsertion =
                PagingData.from(insert_dummySuccess_HomeState).collectData()

            awaitItem().collectData() shouldBeEqualTo expectedListAfterInsertion

            cancelAndIgnoreRemainingEvents()

        }
    }

    @Test
    fun testRequestArticles_Error() = runTest {
        val dispatcherProvider = DefaultDispatcherProvider()
        val repo = FakeArticlesRepositoryImp()
        val useCase = GetArticlesUseCase(repo)
        val viewModel = ArticleViewModel(useCase, dispatcherProvider)

        viewModel.requestArticles(Constants.THROW_ERROR)

        viewModel.articles.test {
            val expectedList = PagingData.empty<ArticleUIModel>().collectData()
            awaitItem().collectData() shouldBeEqualTo expectedList
            cancelAndIgnoreRemainingEvents()
        }
    }

}

private fun <T : Any> PagingData<T>.collectData(): List<T> = runBlocking {
    val items = mutableListOf<T>()
    flowOf(this@collectData).collect { pagingData ->
        pagingData.map { item ->
            items.add(item)
        }
    }
    items
}