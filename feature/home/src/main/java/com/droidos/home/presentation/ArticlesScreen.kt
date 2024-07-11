package com.droidos.home.presentation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.droidos.home.domain.model.ArticleUIModel
import com.muhammadsayed.design.components.CircularProgress
import com.muhammadsayed.design.components.ErrorDialog
import com.muhammadsayed.design.components.LoadingPage


@Composable
fun ArticlesRoute(onNavToDetails: (Int) -> Unit) {
    val viewModel: ArticleViewModel = hiltViewModel()
    val articles = viewModel.uiState.collectAsLazyPagingItems()
    val articleState: LazyListState = rememberLazyListState()

    ArticlesScreen(
        articles = articles,
        articleState = articleState,
        onNavToDetails = onNavToDetails
    )
}

@Composable
fun ArticlesScreen(
    articles: LazyPagingItems<ArticleUIModel>,
    articleState: LazyListState,
    onNavToDetails: (Int) -> Unit
) {
    var showDialog by remember { mutableStateOf(true) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = articleState
    ) {

        items(count = articles.itemCount) {
            articles[it]?.let { article ->
                Text(text = article.title)
            }
        }
    }

    articles.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                LoadingPage(modifier = Modifier.fillMaxSize())
            }

            loadState.refresh is LoadState.Error -> {
                val error = articles.loadState.refresh as LoadState.Error
                Log.e("HomeViewModel refresh", "requestProducts: ${error.error.message}")
                AnimatedVisibility(visible = showDialog) {
                    ErrorDialog(errorMessage = error.error.localizedMessage!!,
                        onRetryClick = { retry() },
                        onDismiss = { showDialog = false })
                }

            }

            loadState.append is LoadState.Loading -> {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgress(true)
                }

            }
        }
    }

}