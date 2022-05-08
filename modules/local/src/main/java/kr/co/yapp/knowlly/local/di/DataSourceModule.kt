package kr.co.yapp.knowlly.local.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.yapp.knowlly.data.source.AuthLocalDataSource
import kr.co.yapp.knowlly.local.source.AuthLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindAuthLocalDataSource(source: AuthLocalDataSourceImpl): AuthLocalDataSource
}
