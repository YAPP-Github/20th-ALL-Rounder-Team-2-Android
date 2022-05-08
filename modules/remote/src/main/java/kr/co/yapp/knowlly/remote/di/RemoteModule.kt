package kr.co.yapp.knowlly.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.yapp.knowlly.remote.api.BaseUrl
import kr.co.yapp.knowlly.remote.api.Interceptors
import kr.co.yapp.knowlly.remote.api.baseUrl
import kr.co.yapp.knowlly.remote.api.createOkhttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: BaseUrl,
        interceptors: Interceptors,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(createOkhttpClient(interceptors))
        .build()
}
