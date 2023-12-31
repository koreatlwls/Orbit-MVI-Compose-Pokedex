package koreatlwls.pokedex.core.model

data class PokemonInfo(
    val id : String,
    val name : String,
    val height : String,
    val weight : String,
    val exp : String,
    val types : List<PokemonType>,
    val imageUrl : String,
)

data class PokemonType(
    val name : String
)
