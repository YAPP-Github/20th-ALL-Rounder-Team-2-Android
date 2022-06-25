package kr.co.knowledgerally.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.knowledgerally.BuildConfig
import kr.co.knowledgerally.data.provider.AccessTokenProvider
import kr.co.knowledgerally.domain.model.VersionName
import kr.co.knowledgerally.log.Logger
import kr.co.knowledgerally.remote.api.AccessTokenInterceptor
import kr.co.knowledgerally.remote.api.BaseUrl
import kr.co.knowledgerally.remote.api.Interceptors
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Provides
    fun provideBaseUrl(): BaseUrl = BaseUrl("http://knowllydev.hkpark.net/api/")

    @Provides
    fun provideInterceptors(
        provider: AccessTokenProvider,
    ): Interceptors {
        val logger = HttpLoggingInterceptor.Logger { message ->
            Logger.d("OkHttp", message)
        }
        val loggingInterceptor = HttpLoggingInterceptor(logger)
            .apply { level = HttpLoggingInterceptor.Level.BODY }
        val accessTokenInterceptor = AccessTokenInterceptor(provider)
        return Interceptors(loggingInterceptor, accessTokenInterceptor)
    }

    @Provides
    fun provideVersionName() = VersionName(BuildConfig.VERSION_NAME)
}
