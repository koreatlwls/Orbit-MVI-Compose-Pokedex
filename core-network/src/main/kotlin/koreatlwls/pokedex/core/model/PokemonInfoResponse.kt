package koreatlwls.pokedex.core.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonInfoResponse(
    val id : Int,
    val name : String,
    val height : Int,
    val weight : Int,
)