package koreatlwls.pokedex.core.service

import koreatlwls.pokedex.core.model.PokemonInfoResponse
import koreatlwls.pokedex.core.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("api/v2/pokemon/")
    suspend fun getPokemons(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20,
    ): Result<PokemonResponse>

    @GET("api/v2/pokemon/{name}")
    suspend fun getPokemonInfo(@Path("name") name: String): Result<PokemonInfoResponse>

}