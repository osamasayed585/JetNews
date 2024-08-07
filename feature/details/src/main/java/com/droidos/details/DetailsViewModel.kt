package com.droidos.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.droidos.common.base.BaseViewModel
import com.droidos.common.base.RequestResult
import com.droidos.common.utils.Constants
import com.droidos.details.event.DetailsEvent
import com.droidos.details.state.DetailsUiState
import com.droidos.domain.useCases.GetArticleDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getArticleDetailsUseCase: GetArticleDetailsUseCase,
    savedStateHandle: SavedStateHandle = SavedStateHandle(),
) : BaseViewModel<DetailsUiState, DetailsEvent>(DetailsUiState()) {

    init {
        val title = savedStateHandle.get<String>(Constants.TITLE_KEY) ?: Constants.ALL
        requestArticleDetails(title)
    }

    /**
     * Handles side effects emitted by the ViewModel and updates the UI state accordingly.
     *
     * @param oldState The current UI state.
     * @param sideEffect The side effect to handle.
     */
    override fun reduce(oldState: DetailsUiState, sideEffect: DetailsEvent) {
        when (sideEffect) {
            is DetailsEvent.OnGetArticleDetails -> createNewState(
                oldState.copy(
                    isLoading = false,
                    title = sideEffect.article.title,
                    image = sideEffect.article.image,
                    description = sideEffect.article.description,
                    publishedAt = sideEffect.article.publishedAt,
                    sourceName = sideEffect.article.sourceName,
                )
            )

            is DetailsEvent.OnGetError -> createNewState(
                oldState.copy(
                    isLoading = false,
                    errorMessage = sideEffect.message
                )
            )
        }
    }


    /**
     * Requests article details for the given title.
     *
     * Launches a coroutine on the main dispatcher to fetch article details using the `getArticleDetailsUseCase`.
     * Emits a `DetailsEvent.OnGetArticleDetails` event with the article details if successful,
     * or a `DetailsEvent.OnGetError` event with the error message if an error occurs.
     *
     * @param title The title of the article to request details for.
     */
    fun requestArticleDetails(title: String) {
        viewModelScope.launch {
            when (val response = getArticleDetailsUseCase.invoke(title)) {
                is RequestResult.Success ->
                    emitEvent(DetailsEvent.OnGetArticleDetails(response.data))

                is RequestResult.Error ->
                    emitEvent(DetailsEvent.OnGetError(response.message))
            }
        }
    }
}