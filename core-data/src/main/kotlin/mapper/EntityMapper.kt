package mapper

import model.Pokemon
import model.PokemonEntity

internal fun Pokemon.toEntity() = PokemonEntity(
    name = name,
    url = url
)