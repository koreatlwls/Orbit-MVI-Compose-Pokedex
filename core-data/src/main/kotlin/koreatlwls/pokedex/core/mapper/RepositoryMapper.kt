package koreatlwls.pokedex.core.mapper

import androidx.paging.PagingData
import androidx.paging.map
import koreatlwls.pokedex.core.model.Pokemon
import koreatlwls.pokedex.core.model.PokemonEntity

internal fun PagingData<PokemonEntity>.toRepository() = map {
    it.toRepository()
}

internal fun PokemonEntity.toRepository() = Pokemon(
    name = name,
    url = url,
    imageUrl = imageUrl,
)