package kr.co.knowledgerally.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.knowledgerally.data.source.AuthRemoteDataSource
import kr.co.knowledgerally.data.source.CategoryRemoteDataSource
import kr.co.knowledgerally.data.source.UserRemoteDataSource
import kr.co.knowledgerally.remote.source.AuthRemoteDataSourceImpl
import kr.co.knowledgerally.remote.source.CategoryRemoteDataSourceImpl
import kr.co.knowledgerally.remote.source.UserRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindAuthDataSource(source: AuthRemoteDataSourceImpl): AuthRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindUserDataSource(source: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindCategoryDataSource(source: CategoryRemoteDataSourceImpl): CategoryRemoteDataSource
}
