package koreatlwls.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import koreatlwls.pokedex.model.PokemonUi
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
                onNavigateToDetail = { pokemonUi ->
                    navHostController.navigate("detail/${pokemonUi.toJson()}")
                }
            )
        }
        composable(
            route = "detail/{pokemonUi}",
            arguments = listOf(navArgument("pokemonUi") { type = PokemonUiNavType() })
        ) { navBackStackEntry ->
            val pokemonUi = navBackStackEntry.arguments?.getParcelable<PokemonUi>("pokemonUi")
            pokemonUi?.let {
                DetailScreen(
                    pokemonUi = it,
                    onBack = {
                        navHostController.popBackStack()
                    }
                )
            }
        }
    }
}