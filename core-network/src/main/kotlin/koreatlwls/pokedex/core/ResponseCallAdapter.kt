package koreatlwls.pokedex.core

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class ResponseCallAdapter<T>(
    private val successType: Type
) : CallAdapter<T, Call<Result<T>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<T>): Call<Result<T>> = ResponseCall(call)

}