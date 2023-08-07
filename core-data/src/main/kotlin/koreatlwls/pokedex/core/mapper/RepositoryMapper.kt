package koreatlwls.pokedex.core.mapper

import androidx.paging.PagingData
import androidx.paging.map
import koreatlwls.pokedex.core.model.Pokemon
import koreatlwls.pokedex.core.model.PokemonEntity
import koreatlwls.pokedex.core.model.PokemonInfo
import koreatlwls.pokedex.core.model.PokemonInfoResponse
import koreatlwls.pokedex.core.model.PokemonType

internal fun PagingData<PokemonEntity>.toRepository() = map {
    it.toRepository()
}

internal fun PokemonEntity.toRepository() = Pokemon(
    name = name,
    imageUrl = imageUrl,
)

internal fun PokemonInfoResponse.toRepository() = PokemonInfo(
    id = getIdString(),
    name = name,
    height = getHeightString(),
    weight = getWeightString(),
    exp = getExpString(),
    types = types.map { it.toRepository() },
    imageUrl = getImageUrl()
)

internal fun PokemonInfoResponse.TypeResponse.toRepository() = PokemonType(
    name = this.type.name
)