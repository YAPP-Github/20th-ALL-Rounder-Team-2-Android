package kr.co.yapp.knowlly.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.yapp.knowlly.remote.api.BaseUrl
import kr.co.yapp.knowlly.remote.api.Interceptors
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Provides
    fun provideBaseUrl(): BaseUrl = BaseUrl("https://randomuser.me")

    @Provides
    fun provideInterceptors(): Interceptors {
        val logger = HttpLoggingInterceptor.Logger {
            Log.d("OkHttp", it)
        }
        val loggingInterceptor = HttpLoggingInterceptor(logger)
        return Interceptors(loggingInterceptor)
    }
}