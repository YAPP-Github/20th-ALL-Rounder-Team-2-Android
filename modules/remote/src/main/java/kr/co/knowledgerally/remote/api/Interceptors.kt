package kr.co.knowledgerally.remote.api

import okhttp3.Interceptor

data class Interceptors(
    val interceptors: List<Interceptor>,
    val networkInterceptors: List<Interceptor>
) {

    companion object {
        val Empty = Interceptors(emptyList(), emptyList())
    }
}
