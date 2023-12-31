package koreatlwls.pokedex.core.service

import koreatlwls.pokedex.core.model.PokemonInfoResponse
import koreatlwls.pokedex.core.model.PokemonResponse
import javax.inject.Inject

class PokemonClient @Inject constructor(
    private val pokemonService: PokemonService
) {

    suspend fun getPokemons(page: Int): Result<PokemonResponse> =
        pokemonService.getPokemons(
            offset = page * PAGING_SIZE,
            limit = PAGING_SIZE
        )

    suspend fun getPokemonInfo(name: String): Result<PokemonInfoResponse> =
        pokemonService.getPokemonInfo(name = name)

    companion object {
        private const val PAGING_SIZE = 20
    }

}