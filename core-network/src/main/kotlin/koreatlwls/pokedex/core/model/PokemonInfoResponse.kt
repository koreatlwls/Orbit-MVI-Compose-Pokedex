package koreatlwls.pokedex.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonInfoResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    @field : Json(name = "base_experience") val experience: Int,
    val types: List<TypeResponse>
) {

    fun getIdString(): String = String.format("#%03d", id)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getExpString(): String = String.format("%03d EXP", experience)

    @JsonClass(generateAdapter = true)
    data class TypeResponse(
        val slot: Int,
        val type: Type
    )

    @JsonClass(generateAdapter = true)
    data class Type(
        val name: String
    )

}