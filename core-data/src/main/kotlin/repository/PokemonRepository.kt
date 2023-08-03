package repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import model.Pokemon

interface PokemonRepository {

    fun getPokemons(): Flow<PagingData<Pokemon>>

}