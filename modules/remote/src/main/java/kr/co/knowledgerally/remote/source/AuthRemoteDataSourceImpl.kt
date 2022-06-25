package kr.co.knowledgerally.remote.source

import kr.co.knowledgerally.data.model.JwtTokenEntity
import kr.co.knowledgerally.data.model.ProviderTokenEntity
import kr.co.knowledgerally.data.source.AuthRemoteDataSource
import kr.co.knowledgerally.remote.api.ApiService
import kr.co.knowledgerally.remote.model.toRemote
import javax.inject.Inject

internal class AuthRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : AuthRemoteDataSource {

    override suspend fun signUp(providerToken: ProviderTokenEntity): Result<JwtTokenEntity> =
        runCatching {
            val response = apiService.signUp(providerToken.toRemote())
            response.data.jwtToken.toData()
        }

    override suspend fun withdrawal(): Result<Unit> = runCatching {
        apiService.withdrawal()
    }
}
