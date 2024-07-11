package com.droidos.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.droidos.common.di.MainDispatcher
import com.droidos.common.utils.Constants
import com.droidos.home.data.mapper.toArticleUIModel
import com.droidos.home.domain.model.ArticleUIModel
import com.droidos.home.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val articlesUseCase: GetArticlesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<PagingData<ArticleUIModel>>(PagingData.empty())
    val uiState: StateFlow<PagingData<ArticleUIModel>> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = PagingData.empty(),
    )

    init {
//        requestArticles()
        requestArticles()
    }


    /**
     * Requests articles based on the provided query.
     *
     * @param query The search query to use for fetching articles. Defaults to [Constants.ALL] to fetch all articles.
     */
//    private fun requestArticles(query: String = Constants.ALL) {
//        Timber.d("requestArticles: $3333")
//        articlesUseCase.invoke(query)
//            .cachedIn(viewModelScope)
//            .onEach {
//                _uiState.tryEmit(it.map { article -> article.toArticleUIModel() })
//                Timber.d("requestArticles: $it")
//            }
//            .launchIn(viewModelScope)
//    }


    private fun requestArticles(query: String = Constants.ALL) {
        Log.e("HomeViewModel", "requestProducts: ")
        viewModelScope.launch(Dispatchers.Main) {
            articlesUseCase.invoke(query)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    Log.e("HomeViewModel", "requestProducts: ${e.message}")
                }
                .cachedIn(viewModelScope)
                .collect {
                    _uiState.tryEmit(it.map { article -> article.toArticleUIModel() })
                    Timber.d("requestProducts: $it")
                }

        }
    }


}