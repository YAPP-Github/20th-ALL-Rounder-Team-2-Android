package com.yapp.remote.di

import com.yapp.data.source.UserRemoteDataSource
import com.yapp.remote.source.UserRemoteDataSourceImpl
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
    abstract fun bindUserRemoteDataSource(sourceImpl: UserRemoteDataSourceImpl) : UserRemoteDataSource
}