package kr.co.knowledgerally.data.source

import kr.co.knowledgerally.data.model.JwtTokenEntity
import kr.co.knowledgerally.data.model.ProviderTokenEntity

interface AuthRemoteDataSource {

    suspend fun signUp(providerToken: ProviderTokenEntity): Result<JwtTokenEntity>

    suspend fun withdrawal(): Result<Unit>
}
