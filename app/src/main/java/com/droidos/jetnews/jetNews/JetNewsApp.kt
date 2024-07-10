package com.droidos.jetnews.jetNews

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.droidos.jetcoffee.R
import com.droidos.jetnews.units.networkMonitor.NetworkMonitor

@Composable
fun JetNewsApp(
    networkMonitor: NetworkMonitor,
    navController: NavHostController,
    appState: JetCoffeeState = rememberJetCoffeeState(
        networkMonitor = networkMonitor,
        navController = navController
    ),
) {
    val notConnectedMessage = stringResource(R.string.not_connected)
    val snackbarHostState = remember { SnackbarHostState() }
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()

    /**
     *  Show a snackbar whenever there's a connection issue.
     */
    LaunchedEffect(isOffline) {
        if (isOffline) {
            snackbarHostState.showSnackbar(
                message = notConnectedMessage,
                duration = SnackbarDuration.Indefinite,
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->
        innerPadding.apply {
            JetNewsNavHost()
        }
    }

}