package com.caiqueluz.kryptos.network

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Inject

class ApiClientConfig @Inject constructor(
    private val baseUrl: String,
    private val retrofitBuilder: Retrofit.Builder,
    private val converterFactory: Converter.Factory,
    private val okHttpClient: OkHttpClient
) {

    fun create(): Retrofit = retrofitBuilder
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .client(okHttpClient)
        .build()
}
