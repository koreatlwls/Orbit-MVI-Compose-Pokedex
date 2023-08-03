package koreatlwls.pokedex.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class RemoteKeys {
    POKEMONS {
        override fun toString(): String = this.name.lowercase()
    }
}

@Entity
data class RemoteKey(
    @PrimaryKey
    val key: String,
    val subKey: String?,
    val nextPage: Int?
)
