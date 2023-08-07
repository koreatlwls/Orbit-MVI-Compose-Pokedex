package koreatlwls.pokedex.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import koreatlwls.pokedex.ui.navigation.MainNavigationGraph
import pokedex.ui.theme.PdsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PdsTheme {
                val navHostController = rememberNavController()
                MainNavigationGraph(navHostController = navHostController)
            }
        }
    }
}

