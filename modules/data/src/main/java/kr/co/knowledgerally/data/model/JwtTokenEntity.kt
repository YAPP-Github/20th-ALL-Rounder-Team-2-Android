package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.JwtToken

data class JwtTokenEntity(
    val accessToken: String,
    val refreshToken: String,
)

internal fun JwtTokenEntity.toDomain() = JwtToken(
    accessToken = accessToken,
    refreshToken = refreshToken,
)
