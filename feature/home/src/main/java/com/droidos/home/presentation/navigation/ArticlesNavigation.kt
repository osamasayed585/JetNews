package com.droidos.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidos.common.utils.Screen
import com.droidos.home.presentation.ArticlesRoute


fun NavGraphBuilder.articlesScreen(
    onNavToDetails: () -> Unit,
) {
    composable(Screen.HomeScreen.route) {
        ArticlesRoute(onNavToDetails)
    }
}


fun NavController.navigateToArticleWithClearStack() = navigate(Screen.HomeScreen.route) { popUpTo(0) }
