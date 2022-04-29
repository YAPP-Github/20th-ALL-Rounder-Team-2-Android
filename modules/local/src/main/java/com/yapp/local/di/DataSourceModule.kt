package com.yapp.local.di

import com.yapp.data.source.UserLocalDataSource
import com.yapp.local.source.UserLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindUserLocalDataSource(source: UserLocalDataSourceImpl): UserLocalDataSource
}