package com.droidos.details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.droidos.common.utils.Constants.TITLE_KEY
import com.droidos.common.utils.Screen
import com.droidos.details.ArticleDetailsRoute


fun NavGraphBuilder.articleDetailsScreen() {
    composable(
        route = Screen.DetailScreen.route + "/{${TITLE_KEY}}",
        arguments = listOf(navArgument(TITLE_KEY) { type = NavType.StringType })
    ) {
        ArticleDetailsRoute()
    }
}


fun NavController.navigateToArticleDetails(title: String) =
    navigate(Screen.DetailScreen.route + "/${title}")
