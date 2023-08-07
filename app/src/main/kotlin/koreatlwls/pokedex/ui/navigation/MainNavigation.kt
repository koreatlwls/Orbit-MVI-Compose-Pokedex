package koreatlwls.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import koreatlwls.pokedex.ui.detail.DetailScreen
import koreatlwls.pokedex.ui.main.MainScreen

@Composable
fun MainNavigationGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = "main",
    ) {
        composable("main") {
            MainScreen(
                onNavigateToDetail = { pokemonName ->
                    navHostController.navigate("detail/${pokemonName}")
                }
            )
        }
        composable(
            route = "detail/{pokemonName}",
            arguments = listOf(navArgument("pokemonName") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val pokemonName = navBackStackEntry.arguments?.getString("pokemonName")
            pokemonName?.let {
                DetailScreen(
                    pokemonName = it,
                    onBack = {
                        navHostController.popBackStack()
                    }
                )
            }
        }
    }
}