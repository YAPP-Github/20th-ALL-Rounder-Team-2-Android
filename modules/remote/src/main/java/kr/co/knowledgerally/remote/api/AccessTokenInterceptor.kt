package kr.co.knowledgerally.remote.api

import kr.co.knowledgerally.data.provider.AccessTokenProvider
import okhttp3.Interceptor
import okhttp3.Response

class AccessTokenInterceptor(
    private val accessTokenProvider: AccessTokenProvider,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = accessTokenProvider.value
        return if (accessToken.isBlank()) {
            chain.proceed(chain.request())
        } else {
            val request = chain.request()
                .newBuilder()
                .addHeader(X_AUTH_TOKEN, accessToken)
                .build()

            chain.proceed(request)
        }
    }

    companion object {
        private const val X_AUTH_TOKEN = "X-AUTH-TOKEN"
    }
}
