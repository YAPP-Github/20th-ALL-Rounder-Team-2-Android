package kr.co.yapp.knowlly.remote.api

import okhttp3.Interceptor

data class Interceptors(val value: List<Interceptor>) {

    constructor(vararg interceptors: Interceptor) : this(interceptors.toList())
}
