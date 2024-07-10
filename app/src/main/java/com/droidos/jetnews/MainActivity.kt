package com.droidos.jetnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.droidos.jetnews.units.networkMonitor.NetworkMonitor
import com.droidos.jetnews.jetNews.JetNewsApp
import com.droidos.jetnews.ui.theme.JetNewsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetNewsTheme {
                val navController = rememberNavController()
                JetNewsApp(networkMonitor, navController)
            }
        }
    }
}
