package kr.co.knowledgerally.data.repo

import kr.co.knowledgerally.data.source.UserRemoteDataSource
import kr.co.knowledgerally.domain.repo.UserRepository
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun isOnboarded(): Result<Boolean> = userRemoteDataSource.isOnboarded()
}
