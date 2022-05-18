package kr.co.knowledgerally.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.knowledgerally.log.Logger
import kr.co.knowledgerally.remote.api.BaseUrl
import kr.co.knowledgerally.remote.api.Interceptors
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Provides
    fun provideBaseUrl(): BaseUrl = BaseUrl("https://randomuser.me")

    @Provides
    fun provideInterceptors(): Interceptors {
        val logger = HttpLoggingInterceptor.Logger { message ->
            Logger.d("OkHttp", message)
        }
        val loggingInterceptor = HttpLoggingInterceptor(logger)
        return Interceptors(loggingInterceptor)
    }
}
