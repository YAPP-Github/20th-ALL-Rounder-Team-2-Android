package kr.co.yapp.knowlly.remote.api

import okhttp3.OkHttpClient

internal fun createOkhttpClient(interceptors: Interceptors): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptors(interceptors)
        .build()

internal fun OkHttpClient.Builder.addInterceptors(interceptors: Interceptors) = apply {
    interceptors.value.forEach(::addInterceptor)
}
