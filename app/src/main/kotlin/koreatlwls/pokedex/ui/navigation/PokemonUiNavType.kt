package koreatlwls.pokedex.ui.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import koreatlwls.pokedex.model.PokemonUi
import koreatlwls.pokedex.util.getParcelable

class PokemonUiNavType : NavType<PokemonUi>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): PokemonUi? =
        PokemonUi::class.getParcelable(bundle, key)

    @OptIn(ExperimentalStdlibApi::class)
    override fun parseValue(value: String): PokemonUi {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter<PokemonUi>()

        return jsonAdapter.fromJson(value)!!
    }

    override fun put(bundle: Bundle, key: String, value: PokemonUi) {
        bundle.putParcelable(key, value)
    }
}