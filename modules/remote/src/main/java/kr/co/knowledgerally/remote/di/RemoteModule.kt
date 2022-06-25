package kr.co.knowledgerally.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.knowledgerally.remote.api.ApiService
import kr.co.knowledgerally.remote.api.BaseUrl
import kr.co.knowledgerally.remote.api.Interceptors
import kr.co.knowledgerally.remote.api.baseUrl
import kr.co.knowledgerally.remote.api.createOkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteModule {

    @Provides
    @Singleton
    fun provideApiService(
        baseUrl: BaseUrl,
        interceptors: Interceptors,
    ): ApiService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(createOkHttpClient(interceptors))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}
