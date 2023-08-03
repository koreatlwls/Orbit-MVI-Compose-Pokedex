package mapper

import androidx.paging.PagingData
import androidx.paging.map
import model.Pokemon
import model.PokemonEntity

internal fun PagingData<PokemonEntity>.toRepository() = map {
    it.toRepository()
}

internal fun PokemonEntity.toRepository() = Pokemon(
    name = name,
    url = url
)