package com.droidos.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidos.common.utils.Screen
import com.droidos.home.ArticlesRoute


fun NavGraphBuilder.articlesScreen(
    onNavToDetails: (String) -> Unit,
) {
    composable(Screen.HomeScreen.route) {
        ArticlesRoute(onNavToDetails)
    }
}

