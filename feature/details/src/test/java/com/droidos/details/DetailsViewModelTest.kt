package com.droidos.details

import com.droidos.common.di.DispatcherProvider
import com.droidos.common.utils.Constants
import com.droidos.domain.useCases.GetArticleDetailsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class DetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var useCase: GetArticleDetailsUseCase
    private lateinit var dispatcher: DispatcherProvider

    @Before
    fun setUp() {
        val fakeDetailsUseCase = FakeArticleDetailsRepositoryImp()
        useCase = GetArticleDetailsUseCase(fakeDetailsUseCase)
        dispatcher = TestDispatcherProvider(StandardTestDispatcher())
    }

    @Test
    fun `requestArticleDetails with all articles returns success and updates uiState`() = runTest {
        val viewModel = DetailsViewModel(useCase, dispatcher)

        viewModel.requestArticleDetails(Constants.ALL)

        assertEquals(dummySuccess_DetailsState, viewModel.uiState.value)
    }

    @Test
    fun `requestArticleDetails with specific title returns success and updates uiState`() = runTest {
        val viewModel = DetailsViewModel(useCase, dispatcher)

        viewModel.requestArticleDetails(SPECIFIC_TITLE)

        assertEquals(dummySuccess_SpecificTitleDetail, viewModel.uiState.value)
    }

    @Test
    fun `requestArticleDetails with empty title returns error and updates uiState`() = runTest {
        val viewModel = DetailsViewModel(useCase, dispatcher)

        viewModel.requestArticleDetails("")

        assertEquals(dummyError_DetailsState, viewModel.uiState.value)
    }
}


