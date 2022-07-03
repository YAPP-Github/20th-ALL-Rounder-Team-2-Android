package kr.co.knowledgerally.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.knowledgerally.data.repo.AppMetaRepositoryImpl
import kr.co.knowledgerally.data.repo.AuthRepositoryImpl
import kr.co.knowledgerally.data.repo.LectureRepositoryImpl
import kr.co.knowledgerally.data.repo.UserRepositoryImpl
import kr.co.knowledgerally.domain.repo.AppMetaRepository
import kr.co.knowledgerally.domain.repo.AuthRepository
import kr.co.knowledgerally.domain.repo.LectureRepository
import kr.co.knowledgerally.domain.repo.UserRepository
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
    abstract fun bindAppMetaRepository(repo: AppMetaRepositoryImpl): AppMetaRepository

    @Binds
    @Singleton
    abstract fun bindLessonRepository(repo: LectureRepositoryImpl): LectureRepository
}
