package com.droidos.details

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidos.details.event.DetailsEvent
import com.droidos.details.state.DetailsUiState
import com.muhammadsayed.design.components.ErrorScreen
import com.muhammadsayed.design.components.HandleError
import com.muhammadsayed.design.components.LoadingImage
import com.muhammadsayed.design.theme.JetNewsTheme


@Composable
fun ArticleDetailsRoute(snackbarHostState: SnackbarHostState) {
    val viewModel: DetailsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    HandleError(
        snackbarHostState = snackbarHostState,
        error = uiState.errorEntity,
        onDismiss = { viewModel.emitEvent(DetailsEvent.ClearError) }
    )

    ArticleDetailsScreen(
        uiState = uiState,
        onRetry = {
            viewModel.requestArticleDetails("new")
        }
    )

}

@Composable
fun ArticleDetailsScreen(uiState: DetailsUiState, onRetry: () -> Unit) {
    if (uiState.title.isNotEmpty())
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            LoadingImage(
                url = uiState.image,
                description = stringResource(com.droidos.common.R.string.this_is_an_image_of_article),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 4.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = uiState.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = uiState.description,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    else Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ErrorScreen { onRetry() }
    }

}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun ArticleDetailsPreview_Dark() {
    JetNewsTheme {
        ArticleDetailsScreen(uiState = DetailsUiState()) {}
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun ArticleDetailsPreview_Light() {
    JetNewsTheme {
        ArticleDetailsScreen(uiState = DetailsUiState()) {}
    }
}