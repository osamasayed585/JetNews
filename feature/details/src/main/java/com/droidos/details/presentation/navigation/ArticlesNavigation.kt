package com.droidos.details.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidos.common.utils.Screen


fun NavGraphBuilder.detailsScreen() {
    composable(Screen.DetailScreen.route) {
        Column {
            Text(text = "Hello World!")
        }
    }
}


fun NavController.navigateToDetailWithClearStack() = navigate(Screen.DetailScreen.route) { popUpTo(0) }
