package kr.co.knowledgerally.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.knowledgerally.data.repo.AppMetaRepositoryImpl
import kr.co.knowledgerally.data.repo.AuthRepositoryImpl
import kr.co.knowledgerally.data.repo.BallRepositoryImpl
import kr.co.knowledgerally.data.repo.UserRepository
import kr.co.knowledgerally.data.repo.UserRepositoryImpl
import kr.co.knowledgerally.domain.repo.AppMetaRepository
import kr.co.knowledgerally.domain.repo.AuthRepository
import kr.co.knowledgerally.domain.repo.BallRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(repo: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(repo: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindBallRepository(repo: BallRepositoryImpl): BallRepository

    @Binds
    abstract fun bindAppMetaRepository(repo: AppMetaRepositoryImpl): AppMetaRepository
}
