package com.droidos.navigation.jetNews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.droidos.common.R
import com.droidos.navigation.util.networkMonitor.NetworkMonitor
import com.muhammadsayed.design.components.JetNewsTopBar

@Composable
fun MyJetNewsApp(
    networkMonitor: NetworkMonitor,
    navController: NavHostController,
    appState: JetNewsState = rememberJetNewsState(
        networkMonitor = networkMonitor,
        navController = navController
    ),
) {
    val notConnectedMessage = stringResource(R.string.not_connected)
    val snackbarHostState = remember { SnackbarHostState() }
    var showSettings by remember { mutableStateOf(false) }
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()

    /**
     *  Show a snackbar whenever there's a connection issue.,
     */
    LaunchedEffect(isOffline, showSettings) {
        if (isOffline) {
            snackbarHostState.showSnackbar(
                message = notConnectedMessage,
                duration = SnackbarDuration.Indefinite,
            )
        }

        if (showSettings) {
            snackbarHostState.showSnackbar(
                message = "soon",
                duration = SnackbarDuration.Short,
            )
        }
    }

    Scaffold(
        topBar = {
            JetNewsTopBar(
                destination = appState.currentDestination,
                onSettingsClick = { showSettings = true }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) { JetNewsNavHost(navController) }
    }

}