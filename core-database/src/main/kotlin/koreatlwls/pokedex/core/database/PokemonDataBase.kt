package koreatlwls.pokedex.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import koreatlwls.pokedex.core.database.dao.PokemonDao
import koreatlwls.pokedex.core.database.dao.RemoteKeyDao
import koreatlwls.pokedex.core.model.PokemonEntity
import koreatlwls.pokedex.core.model.RemoteKey

@Database(
    entities = [
        PokemonEntity::class,
        RemoteKey::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class PokemonDataBase : RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao

    abstract fun getRemoteKeyDao() : RemoteKeyDao

    companion object {
        const val DB_NAME = "pokemon_db"
    }

}