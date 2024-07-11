package com.droidos.jetnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.droidos.navigation.jetNews.MyJetNewsApp
import com.droidos.navigation.util.networkMonitor.NetworkMonitor
import com.muhammadsayed.design.theme.JetNewsTheme
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
                MyJetNewsApp(networkMonitor, navController)
            }
        }
    }
}
