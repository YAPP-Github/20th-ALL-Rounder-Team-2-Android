package kr.co.knowledgerally.remote.api

import retrofit2.Retrofit

internal fun Retrofit.Builder.baseUrl(baseUrl: BaseUrl): Retrofit.Builder = baseUrl(baseUrl.value)
