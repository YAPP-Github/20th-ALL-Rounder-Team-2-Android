package kr.co.knowledgerally.local.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.knowledgerally.data.source.AuthLocalDataSource
import kr.co.knowledgerally.local.source.AuthLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindAuthLocalDataSource(source: AuthLocalDataSourceImpl): AuthLocalDataSource
}
