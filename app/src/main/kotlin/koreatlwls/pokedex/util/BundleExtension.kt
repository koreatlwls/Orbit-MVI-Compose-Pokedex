package koreatlwls.pokedex.util

import android.os.Build
import android.os.Bundle
import kotlin.reflect.KClass

inline fun <reified T : Any> KClass<T>.getParcelable(bundle: Bundle, key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        bundle.getParcelable(key, T::class.java)
    else
        bundle.getParcelable(key)