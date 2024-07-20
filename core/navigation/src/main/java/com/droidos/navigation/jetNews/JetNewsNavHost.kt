package com.droidos.navigation.jetNews

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.droidos.common.utils.Screen
import com.droidos.details.presentation.navigation.detailsScreen
import com.droidos.home.presentation.navigation.articlesScreen

@Composable
fun JetNewsNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        articlesScreen {
            navController.navigate(Screen.DetailScreen.route)
        }

        detailsScreen()
    }
}