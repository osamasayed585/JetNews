package com.droidos.navigation.jetNews

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.droidos.home.presentation.navigation.ArticleRoute
import com.droidos.home.presentation.navigation.articlesScreen

@Composable
fun JetNewsNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ArticleRoute
    ) {
        articlesScreen {

        }
    }
}