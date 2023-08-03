import androidx.room.Database
import androidx.room.RoomDatabase
import db.PokemonDao
import model.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = false
)
internal abstract class PokemonDataBase : RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao

    companion object {
        const val DB_NAME = "pokemon_db"
    }

}