package service

import model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {

    @GET("/pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Result<PokemonResponse>

}