package kr.co.knowledgerally.data.repo

import kr.co.knowledgerally.data.model.toData
import kr.co.knowledgerally.data.source.UserRemoteDataSource
import kr.co.knowledgerally.domain.model.Onboard
import kr.co.knowledgerally.domain.repo.UserRepository
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun isOnboarded(): Result<Boolean> = userRemoteDataSource.isOnboarded()

    override suspend fun submitOnboard(onboard: Onboard): Result<Unit> =
        userRemoteDataSource.submitOnboard(onboard.toData())
}
