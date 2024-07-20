package com.droidos.common.utils

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("homeScreen")
    data object DetailScreen : Screen("detailScreen")
}
