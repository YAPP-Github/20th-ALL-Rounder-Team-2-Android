package kr.co.knowledgerally.data.repo

import kr.co.knowledgerally.data.model.toData
import kr.co.knowledgerally.data.model.toDomain
import kr.co.knowledgerally.data.source.AuthLocalDataSource
import kr.co.knowledgerally.data.source.AuthRemoteDataSource
import kr.co.knowledgerally.domain.model.JwtToken
import kr.co.knowledgerally.domain.model.ProviderToken
import kr.co.knowledgerally.domain.repo.AuthRepository
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource,
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {

    override suspend fun getJwtToken(): Result<JwtToken> = authLocalDataSource
        .getJwtToken()
        .map { it?.toDomain() ?: JwtToken.Empty }

    override suspend fun signUp(providerToken: ProviderToken): Result<JwtToken> =
        authRemoteDataSource.signUp(providerToken.toData())
            .onSuccess { authLocalDataSource.saveJwtToken(it) }
            .map { it.toDomain() }

    override suspend fun withdrawal(): Result<Unit> = authRemoteDataSource
        .withdrawal()
        .onSuccess { authLocalDataSource.logout() }

    override suspend fun logout(): Result<Unit> = authLocalDataSource.logout()
}
