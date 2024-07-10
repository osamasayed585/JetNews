package com.droidos.jetnews.jetNews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.droidos.jetnews.units.networkMonitor.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


@Composable
fun rememberJetCoffeeState(
    navController: NavHostController,
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) = remember(
    navController, networkMonitor, coroutineScope
) { JetCoffeeState(navController, networkMonitor, coroutineScope) }


class JetCoffeeState(
    private val navController: NavHostController,
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope,
) {

    /**
     * Current navigation destination.
     */
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    /**
     * Whether the bottom bar should be shown.
     */
//    val shouldShowBottomBar: Boolean
//        @Composable get() = when (currentDestination?.route) {
//            HomeRoute -> true
//            else -> false
//        }

    /**
     * Whether the top bar should be shown.
     */
//    val shouldShowTopBar: Boolean
//        @Composable get() = when (currentDestination?.route) {
//            HomeRoute -> false
//            else -> true
//        }

    /**
     * String resource id to use in the topBar.
     */
//    val showScreenTitle: String?
//        @Composable get() = when (navController.currentDestination?.route) {
//            HomeRoute -> stringResource(id = R.string.home_screen_title)
//            CartRoute -> stringResource(id = R.string.cart_screen_title)
//            else -> null
//        }

    /**
     * Whether the user is currently connected to the internet.
     */
    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )


    /**
     * Map of top level destinations to be used in the TopBar, BottomBar. The key is the route.
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    /**
     * The top level destinations that have unread news resources.
     */

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {

        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // re-selecting the same item
            launchSingleTop = true
            // Restore state when re-selecting a previously selected item
            restoreState = true
        }
//
//        when (topLevelDestination) {
//            TopLevelDestination.HOME -> navController.navigateToHomeScreen(topLevelNavOptions)
//            TopLevelDestination.CART -> navController.navigateToCartScreen(topLevelNavOptions)
//        }

    }
}