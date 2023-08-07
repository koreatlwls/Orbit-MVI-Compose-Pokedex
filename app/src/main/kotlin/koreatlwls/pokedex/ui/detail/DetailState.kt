package koreatlwls.pokedex.ui.detail

import koreatlwls.pokedex.core.model.PokemonInfo

data class DetailState(
    val pokemonInfo: PokemonInfo? = null,
    val loading: Boolean = true,
)

sealed interface DetailSideEffect {
    data class SnackBar(val message: String) : DetailSideEffect
}