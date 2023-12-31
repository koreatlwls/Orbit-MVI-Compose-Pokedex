package koreatlwls.pokedex.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import koreatlwls.pokedex.core.service.PokemonClient
import koreatlwls.pokedex.core.service.PokemonService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

    @Provides
    @Singleton
    fun providePokemonService(retrofit: Retrofit): PokemonService =
        retrofit.create(PokemonService::class.java)

    @Provides
    @Singleton
    fun providePokemonClient(pokemonService: PokemonService) =
        PokemonClient(pokemonService)

}