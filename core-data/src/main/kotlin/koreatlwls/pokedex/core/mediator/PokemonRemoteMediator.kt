package koreatlwls.pokedex.core.mediator

import koreatlwls.pokedex.core.database.PokemonDataBase
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import koreatlwls.pokedex.core.database.dao.PokemonDao
import koreatlwls.pokedex.core.database.dao.RemoteKeyDao
import koreatlwls.pokedex.core.mapper.toEntity
import koreatlwls.pokedex.core.model.PokemonEntity
import koreatlwls.pokedex.core.model.RemoteKey
import koreatlwls.pokedex.core.model.RemoteKeys
import koreatlwls.pokedex.core.service.PokemonClient

@OptIn(ExperimentalPagingApi::class)
internal class PokemonRemoteMediator(
    private val pokemonDataBase: PokemonDataBase,
    private val pokemonDao: PokemonDao,
    private val remoteKeyDao: RemoteKeyDao,
    private val pokemonClient: PokemonClient
) : RemoteMediator<Int, PokemonEntity>() {

    private lateinit var result: MediatorResult

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        val nextPage = when (loadType) {
            LoadType.REFRESH -> INITIAL_PAGE
            LoadType.PREPEND -> return MediatorResult.Success(
                endOfPaginationReached = true
            )

            LoadType.APPEND -> {
                val remoteKey = pokemonDataBase.withTransaction {
                    remoteKeyDao.remoteKeyByQuery(RemoteKeys.POKEMONS.toString())
                }

                if (remoteKey.nextPage == null) {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                remoteKey.nextPage
            }
        }

        pokemonClient.getPokemons(nextPage ?: 0)
            .onSuccess { response ->
                val last = response.next == null

                pokemonDataBase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        pokemonDao.clearAll()
                        remoteKeyDao.deleteByKey(RemoteKeys.POKEMONS.toString())
                    }

                    remoteKeyDao.upsert(
                        RemoteKey(
                            key = RemoteKeys.POKEMONS.toString(),
                            subKey = null,
                            nextPage = if (last) null else nextPage?.inc()
                        )
                    )

                    pokemonDao.upsertAll(response.items.map { it.toEntity() })
                }

                result = MediatorResult.Success(last)
            }
            .onFailure { error ->
                result = MediatorResult.Error(error)
            }

        return result
    }

    companion object {
        private const val INITIAL_PAGE = 0
    }
}