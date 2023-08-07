package koreatlwls.pokedex.core.mapper

import androidx.paging.PagingData
import androidx.paging.map
import koreatlwls.pokedex.core.model.Pokemon
import koreatlwls.pokedex.core.model.PokemonEntity
import koreatlwls.pokedex.core.model.PokemonInfo
import koreatlwls.pokedex.core.model.PokemonInfoResponse

internal fun PagingData<PokemonEntity>.toRepository() = map {
    it.toRepository()
}

internal fun PokemonEntity.toRepository() = Pokemon(
    name = name,
    imageUrl = imageUrl,
)

internal fun PokemonInfoResponse.toRepository() = PokemonInfo(
    name = name,
    height = height,
    weight = weight
)