package koreatlwls.pokedex.core.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import koreatlwls.pokedex.core.model.Pokemon

interface PokemonRepository {

    fun getPokemons(): Flow<PagingData<Pokemon>>

}