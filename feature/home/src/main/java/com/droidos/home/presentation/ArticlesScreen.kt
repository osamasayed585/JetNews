package com.droidos.home.presentation

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.droidos.common.utils.Constants.DESCRIPTION_MAX_LINES
import com.droidos.common.utils.Constants.TITLE_MAX_LINES
import com.droidos.common.utils.formatTimeAgo
import com.droidos.home.R
import com.droidos.home.domain.model.ArticleUIModel
import com.muhammadsayed.design.components.ErrorScreen
import com.muhammadsayed.design.components.FullScreenLoading
import com.muhammadsayed.design.components.LoadingImage
import com.muhammadsayed.design.theme.JetNewsTheme
import timber.log.Timber


@Composable
fun ArticlesRoute(
    onNavToDetails: () -> Unit,
    viewModel: ArticleViewModel = hiltViewModel(),
) {
    val articles = viewModel.articles.collectAsLazyPagingItems()
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
                when (loadState.append) {
                    is LoadState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(16.dp)
                                .size(60.dp),
                            color = Color.Red,
                            strokeWidth = 2.dp
                        )
                    }

                    is LoadState.NotLoading -> {}

                    is LoadState.Error -> {
                        val error = articles.loadState.append as LoadState.Error
                        Timber.e("ArticlesScreen: ${error.error.localizedMessage}")
                        LimitedCard()
                    }
                }
            }
        }
    }

    articles.apply {
        when (loadState.refresh) {
            is LoadState.Loading -> {
                FullScreenLoading(modifier = Modifier.fillMaxSize())
            }

            is LoadState.NotLoading -> {}

            is LoadState.Error -> {
                ErrorScreen(
                    modifier = Modifier.fillMaxSize(),
                    onRetry = { retry() }
                )
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

        LoadingImage(
            modifier = Modifier.fillMaxWidth(),
            url = uiState.image,
            description = stringResource(R.string.this_is_an_image_of_article),
        )

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = uiState.title,
            maxLines = TITLE_MAX_LINES,
            style = MaterialTheme.typography.titleMedium

        )

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = uiState.description,
            maxLines = DESCRIPTION_MAX_LINES,
            style = MaterialTheme.typography.bodyMedium
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = formatTimeAgo(uiState.publishedAt, LocalContext.current),
                style = MaterialTheme.typography.bodySmall
            )


            Text(
                text = uiState.sourceName,
                style = MaterialTheme.typography.bodySmall
            )
        }
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
                publishedAt = "2024-07-18T10:40:00Z",
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
                publishedAt = "2024-07-17T10:40:00Z",
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