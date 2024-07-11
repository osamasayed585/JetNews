package com.droidos.home.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.droidos.home.R
import com.droidos.home.domain.model.ArticleUIModel
import com.muhammadsayed.design.components.ErrorDialog
import com.muhammadsayed.design.components.FullScreenLoading
import timber.log.Timber


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
    onNavToDetails: (Int) -> Unit,
) {
    var showDialog by remember { mutableStateOf(true) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = articleState
    ) {
        items(count = articles.itemCount) {
            articles[it]?.let { article ->
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = article.title
                )
            }
        }

        item {
            articles.apply {
                when {
                    loadState.append is LoadState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(16.dp)
                                .size(60.dp),
                            color = Color.Red,
                            strokeWidth = 2.dp
                        )
                    }

                    loadState.append is LoadState.Error -> {
                        val error = articles.loadState.append as LoadState.Error
                        Timber.e("ArticlesScreen: ${error.error.localizedMessage}")
                        LimitedCard()
                    }
                }
            }
        }
    }

    articles.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                FullScreenLoading(modifier = Modifier.fillMaxSize())
            }

            loadState.refresh is LoadState.Error -> {
                val error = articles.loadState.refresh as LoadState.Error
                AnimatedVisibility(visible = showDialog) {
                    ErrorDialog(errorMessage = error.error.localizedMessage!!,
                        onRetryClick = { retry() },
                        onDismiss = { showDialog = false })
                }

            }
        }
    }

}

@Preview
@Composable
private fun LimitedCard() {

    Card {
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(R.string.limited_message)
        )

    }
}