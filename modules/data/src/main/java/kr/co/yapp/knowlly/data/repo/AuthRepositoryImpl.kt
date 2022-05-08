package kr.co.yapp.knowlly.data.repo

import kr.co.yapp.knowlly.data.source.AuthLocalDataSource
import kr.co.yapp.knowlly.domain.repo.AuthRepository
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource
) : AuthRepository {

    override suspend fun getAccessToken(): Result<String> = authLocalDataSource.getAccessToken()
}
