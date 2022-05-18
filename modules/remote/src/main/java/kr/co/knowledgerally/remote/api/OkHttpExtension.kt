package kr.co.knowledgerally.remote.api

import okhttp3.OkHttpClient

internal fun createOkHttpClient(interceptors: Interceptors): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptors(interceptors)
        .build()

internal fun OkHttpClient.Builder.addInterceptors(interceptors: Interceptors) = apply {
    interceptors.value.forEach(::addInterceptor)
}
