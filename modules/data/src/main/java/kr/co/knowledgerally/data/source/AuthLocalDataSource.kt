package kr.co.knowledgerally.data.source

import kr.co.knowledgerally.data.model.JwtTokenEntity

interface AuthLocalDataSource {

    suspend fun getJwtToken(): Result<JwtTokenEntity?>

    suspend fun saveJwtToken(jwtToken: JwtTokenEntity): Result<Unit>

    suspend fun logout(): Result<Unit>
}
