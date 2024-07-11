package com.droidos.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidos.home.presentation.ArticlesRoute

const val ArticleRoute = "articlesScreen"

fun NavGraphBuilder.articlesScreen(
    onNavToDetails: (Int) -> Unit,
) {
    composable(ArticleRoute) {
        ArticlesRoute(onNavToDetails)
    }
}


fun NavController.navigateToArticleWithClearStack() = navigate(ArticleRoute) { popUpTo(0) }
