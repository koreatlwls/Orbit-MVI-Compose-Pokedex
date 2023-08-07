package koreatlwls.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import koreatlwls.pokedex.model.PokemonUi
import koreatlwls.pokedex.ui.detail.DetailScreen
import koreatlwls.pokedex.ui.main.MainScreen
import koreatlwls.pokedex.ui.result.ResultScreen
import koreatlwls.pokedex.util.getParcelable

@Composable
fun MainNavigationGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = "main",
    ) {
        composable("main") {
            MainScreen(
                onNavigateToResult = {
                    navHostController.navigate("result/${it}")
                },
                onNavigateToDetail = { pokemonUi ->
                    navHostController.navigate("detail/${pokemonUi.toJson()}")
                }
            )
        }
        composable(
            route = "result/{pokemonName}",
            arguments = listOf(navArgument("pokemonName") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("pokemonName")?.let {
                ResultScreen(
                    name = it,
                    onBack = {
                        navHostController.popBackStack()
                    }
                )
            }
        }
        composable(
            route = "detail/{pokemonUi}",
            arguments = listOf(navArgument("pokemonUi") { type = PokemonUiNavType() })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.let {
                PokemonUi::class.getParcelable(it, "pokemonUi")?.let {
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
}