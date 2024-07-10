@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.droidos.jetnews.jetNews

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Preview
@Composable
fun JetNewsNavHost() {
    SharedTransitionLayout {
        val navController = rememberNavController()

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
}