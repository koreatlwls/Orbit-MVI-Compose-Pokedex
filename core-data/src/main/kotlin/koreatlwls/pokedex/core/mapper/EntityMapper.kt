package koreatlwls.pokedex.core.mapper

import koreatlwls.pokedex.core.model.Item
import koreatlwls.pokedex.core.model.PokemonEntity

internal fun Item.toEntity() = PokemonEntity(
    name = name,
    url = url
)