package com.droidos.home.presentation

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.droidos.common.utils.Constants.TITLE_MAX_LINES
import com.droidos.home.R
import com.droidos.home.domain.model.ArticleUIModel
import com.muhammadsayed.design.components.ErrorDialog
import com.muhammadsayed.design.components.FullScreenLoading
import com.muhammadsayed.design.theme.JetNewsTheme
import timber.log.Timber


@Composable
fun ArticlesRoute(onNavToDetails: () -> Unit) {
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
    onNavToDetails: () -> Unit,
) {
    var showDialog by remember { mutableStateOf(true) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        state = articleState
    ) {
        items(count = articles.itemCount) {
            articles[it]?.let { article ->
                ArticleItem(
                    uiState = article,
                    onCLick = onNavToDetails
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

@Composable
fun ArticleItem(uiState: ArticleUIModel, onCLick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCLick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {

        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(uiState.image)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.this_is_an_image_of_article),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            modifier = Modifier.padding(16.dp),
            text = uiState.title,
            maxLines = TITLE_MAX_LINES,
            style = MaterialTheme.typography.titleMedium

        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun ArticleItemPreview_Light() {
    JetNewsTheme {
        ArticleItem(
            uiState = ArticleUIModel(
                title = "Netanyahu to address US Congress on 24 July",
                image = "integer",
                description = "Why are people in China buying salt and Why are people in China buying salt?",
                publishedAt = "1 hour ago",
                sourceName = "CNN"
            ),
            onCLick = {}
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun ArticleItemPreview_Dark() {
    JetNewsTheme {

        ArticleItem(
            uiState = ArticleUIModel(
                title = "Netanyahu to address US Congress on 24 July",
                image = "integer",
                description = "Why are people in China buying salt and Why are people in China buying salt?",
                publishedAt = "1 hour ago",
                sourceName = "CNN"
            ),
            onCLick = {}
        )
    }
}

@Composable
private fun LimitedCard() {

    Card {
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(R.string.limited_message)
        )

    }
}