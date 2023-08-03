package model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val url: String
)