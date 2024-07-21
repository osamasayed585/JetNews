@file:OptIn(ExperimentalMaterial3Api::class)

package com.muhammadsayed.design.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.droidos.common.utils.Screen
import com.muhammadsayed.design.R


@Composable
fun JetNewsTopBar(
    destination: NavDestination?,
    onSettingsClick: () -> Unit,
) {
    when (destination?.route) {
        Screen.HomeScreen.route -> HomeTopBar(onSettingsClick)
        Screen.DetailScreen.route -> ArticleDetailTopBar()
    }
}

@Composable
fun HomeTopBar(onSettingsClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.jetnews),
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            Icon(
                modifier = Modifier
                    .clickable { onSettingsClick() }
                    .padding(horizontal = 6.dp),
                imageVector = Icons.Filled.Settings,
                contentDescription = stringResource(R.string.settings)
            )
        }
    )
}

@Composable
fun ArticleDetailTopBar() {

    TopAppBar(
        title = { Text(text = stringResource(R.string.detail)) },
        navigationIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back)
            )
        }
    )
}
