package koreatlwls.pokedex.core.repository

import androidx.paging.PagingData
import koreatlwls.pokedex.core.model.Pokemon
import koreatlwls.pokedex.core.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemons(): Flow<PagingData<Pokemon>>

    suspend fun getPokemonInfo(name: String): Result<PokemonInfo>

}