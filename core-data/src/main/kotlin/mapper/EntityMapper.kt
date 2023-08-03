package mapper

import model.Item
import model.PokemonEntity

internal fun Item.toEntity() = PokemonEntity(
    name = name,
    url = url
)