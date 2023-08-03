package db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import model.PokemonEntity

@Dao
internal interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemons: List<PokemonEntity>)

    @Query("SELECT * FROM PokemonEntity ORDER BY ID DESC")
    fun getPokemons(): PagingSource<Int, PokemonEntity>

    @Query("DELETE FROM PokemonEntity")
    suspend fun clearAll()

}