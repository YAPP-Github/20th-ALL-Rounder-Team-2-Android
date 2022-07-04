package kr.co.knowledgerally.domain.repo

import kr.co.knowledgerally.domain.model.JwtToken
import kr.co.knowledgerally.domain.model.ProviderToken

interface AuthRepository {

    suspend fun getJwtToken(): Result<JwtToken>

    suspend fun isSignedUp(providerToken: ProviderToken): Result<Boolean>

    suspend fun signUp(providerToken: ProviderToken): Result<JwtToken>

    suspend fun signIn(providerToken: ProviderToken): Result<JwtToken>

    suspend fun withdrawal(): Result<Unit>
    
    suspend fun logout(): Result<Unit>
}
