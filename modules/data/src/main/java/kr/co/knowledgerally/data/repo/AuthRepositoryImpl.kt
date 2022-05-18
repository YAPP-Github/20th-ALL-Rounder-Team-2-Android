package kr.co.knowledgerally.data.repo

import kr.co.knowledgerally.data.source.AuthLocalDataSource
import kr.co.knowledgerally.domain.repo.AuthRepository
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource
) : AuthRepository {

    override suspend fun getAccessToken(): Result<String> = authLocalDataSource.getAccessToken()
}
