package db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import model.PokemonEntity

@Dao
interface PokemonDao {

    @Upsert
    suspend fun upsertAll(pokemons: List<PokemonEntity>)

    @Query("SELECT * FROM PokemonEntity ORDER BY ID DESC")
    fun getPokemons(): PagingSource<Int, PokemonEntity>

    @Query("DELETE FROM PokemonEntity")
    suspend fun clearAll()

}