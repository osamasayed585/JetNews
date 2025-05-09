package com.droidos.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.droidos.common.base.BaseViewModel
import com.droidos.common.di.DispatcherProvider
import com.droidos.common.utils.Constants
import com.droidos.details.event.DetailsEvent
import com.droidos.details.state.DetailsUiState
import com.droidos.domain.useCases.GetArticleDetailsUseCase
import com.droidos.network.di.errorHandler.entities.asErrorEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getArticleDetailsUseCase: GetArticleDetailsUseCase,
    private val dispatcher: DispatcherProvider,
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
            is DetailsEvent.ClearError -> createNewState(oldState.copy(errorEntity = null))

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
                    errorEntity = sideEffect.type
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
    fun requestArticleDetails(title: String) = viewModelScope.launch(dispatcher.io) {
        getArticleDetailsUseCase(title).fold(
            onSuccess = { article ->
                emitEvent(DetailsEvent.OnGetArticleDetails(article))
            },
            onFailure = { throwable ->
                val asErrorEntity = throwable.asErrorEntity()
                emitEvent(DetailsEvent.OnGetError(asErrorEntity))
            }
        )
    }
}