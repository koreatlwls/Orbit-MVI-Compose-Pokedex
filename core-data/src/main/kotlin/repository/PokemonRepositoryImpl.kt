package repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import database.PokemonDataBase
import database.dao.PokemonDao
import database.dao.RemoteKeyDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mapper.toRepository
import mediator.PokemonRemoteMediator
import model.Pokemon
import service.PokemonClient
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonDataBase: PokemonDataBase,
    private val pokemonDao: PokemonDao,
    private val remoteKeyDao: RemoteKeyDao,
    private val pokemonClient: PokemonClient
) : PokemonRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPokemons(): Flow<PagingData<Pokemon>> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE),
            remoteMediator = PokemonRemoteMediator(
                pokemonDataBase = pokemonDataBase,
                pokemonDao = pokemonDao,
                remoteKeyDao = remoteKeyDao,
                pokemonClient = pokemonClient
            )
        ) {
            pokemonDao.getPokemons()
        }.flow.map {
            it.toRepository()
        }

    companion object {
        private const val PAGE_SIZE = 20
    }

}