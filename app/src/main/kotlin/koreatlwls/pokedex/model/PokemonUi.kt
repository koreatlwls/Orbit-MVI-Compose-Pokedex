package koreatlwls.pokedex.model

import android.os.Parcelable
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonUi(
    val name: String,
    val imageUrl: String,
    val backgroundColor: String = "",
) : Parcelable {

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    @IgnoredOnParcel
    @OptIn(ExperimentalStdlibApi::class)
    private val jsonAdapter = moshi.adapter<PokemonUi>()

    fun toJson(): String = jsonAdapter.toJson(this)

}
