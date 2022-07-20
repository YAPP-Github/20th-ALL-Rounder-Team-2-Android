package kr.co.knowledgerally.remote.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.knowledgerally.data.provider.AccessTokenProvider
import kr.co.knowledgerally.data.provider.RefreshTokenProvider
import kr.co.knowledgerally.remote.api.AccessTokenInterceptor
import kr.co.knowledgerally.remote.api.ApiService
import kr.co.knowledgerally.remote.api.AuthenticationListener
import kr.co.knowledgerally.remote.api.Authenticator
import kr.co.knowledgerally.remote.api.BaseUrl
import kr.co.knowledgerally.remote.api.EnumConverterFactory
import kr.co.knowledgerally.remote.api.Interceptors
import kr.co.knowledgerally.remote.api.LocalDateTimeDeserializer
import kr.co.knowledgerally.remote.api.LocalDateTimeSerializer
import kr.co.knowledgerally.remote.api.RefreshApiService
import kr.co.knowledgerally.remote.api.baseUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteModule {

    @Provides
    @Singleton
    fun provideApiService(
        baseUrl: BaseUrl,
        interceptors: Interceptors,
        accessTokenProvider: AccessTokenProvider,
        refreshTokenProvider: RefreshTokenProvider,
        authenticationListener: AuthenticationListener,
    ): ApiService {
        val authenticator = Authenticator(
            apiService = provideRefreshApiService(baseUrl, interceptors),
            accessTokenProvider = accessTokenProvider,
            refreshTokenProvider = refreshTokenProvider,
            authenticationListener = authenticationListener
        )

        val gson = GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeSerializer)
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializer)
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                createOkHttpClient(interceptors) {
                    addInterceptor(AccessTokenInterceptor(accessTokenProvider))
                    authenticator(authenticator)
                }
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(EnumConverterFactory)
            .build()
            .create(ApiService::class.java)
    }

    private fun provideRefreshApiService(
        baseUrl: BaseUrl,
        interceptors: Interceptors,
    ): RefreshApiService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(createOkHttpClient(interceptors))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RefreshApiService::class.java)

    private fun createOkHttpClient(
        interceptors: Interceptors,
        apply: OkHttpClient.Builder.() -> Unit = { },
    ) = OkHttpClient.Builder()
        .apply {
            interceptors.interceptors.forEach(::addInterceptor)
            interceptors.networkInterceptors.forEach(::addNetworkInterceptor)
        }
        .apply(apply)
        .build()
}
