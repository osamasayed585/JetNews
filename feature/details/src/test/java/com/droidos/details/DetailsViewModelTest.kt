package com.droidos.details

import com.droidos.common.utils.Constants
import com.droidos.domain.useCases.GetArticleDetailsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import kotlin.coroutines.ContinuationInterceptor


class DetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `requestArticleDetails with all articles returns success and updates uiState`() = runTest {
        // Given
        val fakeDetailsUseCase = FakeArticleDetailsRepositoryImp()
        val useCase = GetArticleDetailsUseCase(fakeDetailsUseCase)
        val viewModel = DetailsViewModel(useCase)
        // When
        viewModel.requestArticleDetails(Constants.ALL)

        // Then
        assertEquals(dummySuccess_DetailsState, viewModel.uiState.value)
    }

    @Test
    fun `requestArticleDetails with specific title returns success and updates uiState`() =
        runTest {
            // Given
            val fakeDetailsUseCase = FakeArticleDetailsRepositoryImp()
            val useCase = GetArticleDetailsUseCase(fakeDetailsUseCase)
            val viewModel = DetailsViewModel(useCase)

            // When
            viewModel.requestArticleDetails(SPECIFIC_TITLE)

            // Then
            assertEquals(dummySuccess_SpecificTitleDetail, viewModel.uiState.value)
        }


    @Test
    fun `requestArticleDetails with empty title returns error and updates uiState`() =
        runTest {
            // Given
            val fakeDetailsUseCase = FakeArticleDetailsRepositoryImp()
            val useCase = GetArticleDetailsUseCase(fakeDetailsUseCase)
            val viewModel = DetailsViewModel(useCase)

            // When
            viewModel.requestArticleDetails("")

            // Then
            assertEquals(dummyError_DetailsState, viewModel.uiState.value)
        }


    @Test
    fun `test flow consumer`()= runTest {
        val flow  = flowOf(1,2,3,4,5)
        val res = mutableListOf<Int>()
        flow.collect{
            res.add(it)
        }

        assertEquals(listOf(1,2,3,4,5),res)
    }
}

