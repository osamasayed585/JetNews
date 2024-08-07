package com.droidos.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.droidos.common.di.DispatcherProvider
import com.droidos.common.utils.Constants
import com.droidos.domain.useCases.GetArticlesUseCase
import com.droidos.model.beans.ArticleUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val articlesUseCase: GetArticlesUseCase,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    private val _articles = MutableStateFlow<PagingData<ArticleUIModel>>(PagingData.empty())
    val articles: StateFlow<PagingData<ArticleUIModel>> = _articles.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = PagingData.empty(),
    )

    init {
        requestArticles()
    }


    /**
     * Requests articles based on the provided query
     * @param query The search query to use for fetching articles. Defaults to [Constants.ALL] to fetch all articles.
     */
    fun requestArticles(query: String = Constants.ALL) {
        viewModelScope.launch(dispatcherProvider.main) {
            articlesUseCase.invoke(query)
                .flowOn(dispatcherProvider.io)
                .catch {
                    Timber.e("requestProducts: ${it.message}")
                }
                .cachedIn(viewModelScope)
                .collect {
                    _articles.tryEmit(it)
                }
        }
    }


}