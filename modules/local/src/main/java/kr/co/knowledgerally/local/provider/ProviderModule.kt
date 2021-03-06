package kr.co.knowledgerally.local.provider

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.knowledgerally.data.provider.AccessTokenProvider
import kr.co.knowledgerally.data.provider.RefreshTokenProvider

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderModule {

    @Binds
    abstract fun provideAccessTokenProvider(provider: AccessTokenProviderImpl): AccessTokenProvider

    @Binds
    abstract fun provideRefreshTokenProvider(provider: RefreshTokenProviderImpl): RefreshTokenProvider
}
