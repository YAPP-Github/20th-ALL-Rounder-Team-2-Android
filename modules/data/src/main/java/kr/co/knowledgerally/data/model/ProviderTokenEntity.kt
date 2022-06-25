package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.ProviderToken

data class ProviderTokenEntity(
    val accessToken: String,
    val name: String,
)

internal fun ProviderToken.toData() = ProviderTokenEntity(
    accessToken = accessToken,
    name = name,
)
