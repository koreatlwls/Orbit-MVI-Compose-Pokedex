package koreatlwls.pokedex.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import koreatlwls.pokedex.core.database.dao.PokemonDao
import koreatlwls.pokedex.core.database.PokemonDataBase
import koreatlwls.pokedex.core.database.dao.RemoteKeyDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {

    @Provides
    @Singleton
    fun providePokemonDao(pokemonDataBase: PokemonDataBase): PokemonDao =
        pokemonDataBase.getPokemonDao()

    @Provides
    @Singleton
    fun provideRemoteKeyDao(pokemonDataBase: PokemonDataBase): RemoteKeyDao =
        pokemonDataBase.getRemoteKeyDao()

}