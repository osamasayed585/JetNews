package com.droidos.navigation.jetNews

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.droidos.common.utils.Screen
import com.droidos.details.navigation.articleDetailsScreen
import com.droidos.details.navigation.navigateToArticleDetails
import com.droidos.home.navigation.articlesScreen

@Composable
fun JetNewsNavHost(navController: NavHostController, snackbarHostState: SnackbarHostState) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        articlesScreen(onNavToDetails = navController::navigateToArticleDetails)

        articleDetailsScreen(snackbarHostState)
    }
}