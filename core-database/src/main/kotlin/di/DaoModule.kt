package di

import PokemonDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import db.PokemonDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {

    @Provides
    @Singleton
    fun providePokemonDao(pokemonDataBase: PokemonDataBase): PokemonDao =
        pokemonDataBase.getPokemonDao()

}