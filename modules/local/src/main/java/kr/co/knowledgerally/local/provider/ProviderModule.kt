package kr.co.knowledgerally.local.provider

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.knowledgerally.data.provider.AccessTokenProvider

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderModule {

    @Binds
    abstract fun provideAccessTokenProvider(provider: AccessTokenProviderImpl): AccessTokenProvider
}
