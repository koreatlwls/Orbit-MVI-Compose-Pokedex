package service

import model.PokemonResponse
import javax.inject.Inject

internal class PokemonClient @Inject constructor(
    private val pokemonService: PokemonService
) {

    suspend fun getPokemons(page: Int): Result<PokemonResponse> = pokemonService.getPokemons(
        offset = page * PAGING_SIZE,
        limit = PAGING_SIZE
    )

    companion object {
        private const val PAGING_SIZE = 20
    }

}