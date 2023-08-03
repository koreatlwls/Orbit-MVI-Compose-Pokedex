package koreatlwls.pokedex.core

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

internal class ResponseCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (Call::class.java != getRawType(returnType)) return null
        try {
            check(returnType is ParameterizedType)
        } catch (e: IllegalArgumentException) {
            return null
        }

        val responseType = getParameterUpperBound(0, returnType)

        if (getRawType(responseType) != Result::class.java) return null
        try {
            check(responseType is ParameterizedType)
        } catch (e: IllegalArgumentException) {
            return null
        }

        val successType = getParameterUpperBound(0, responseType)
        return ResponseCallAdapter<Any>(successType)
    }

}