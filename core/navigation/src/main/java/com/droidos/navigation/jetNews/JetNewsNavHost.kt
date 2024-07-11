package com.droidos.navigation.jetNews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun JetNewsNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "HomeRoute"
    ) {
        composable(route = "HomeRoute") {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Hello World!")
            }
        }
//            homeScreen(
//                navToCoffeeDetail = navController::navigateToDetailScreen,
//                sharedTransitionScope = this@SharedTransitionLayout,
//            )
//
//            detailScreen(sharedTransitionScope = this@SharedTransitionLayout)
    }
}