import androidx.room.Database
import androidx.room.RoomDatabase
import db.PokemonDao
import db.RemoteKeyDao
import model.PokemonEntity
import model.RemoteKey

@Database(
    entities = [
        PokemonEntity::class,
        RemoteKey::class,
    ],
    version = 1,
    exportSchema = false
)
internal abstract class PokemonDataBase : RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao

    abstract fun getRemoteKeyDao() : RemoteKeyDao

    companion object {
        const val DB_NAME = "pokemon_db"
    }

}