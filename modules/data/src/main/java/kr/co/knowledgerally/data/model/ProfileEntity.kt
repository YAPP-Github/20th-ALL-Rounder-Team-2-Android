package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.Profile

data class ProfileEntity(
    val username: String,
    val imageUrl: String?,
    val introduction: String,
    val kakaoId: String,
    val portfolio: String,
)

fun ProfileEntity.toDomain() = Profile(
    username = username,
    imageUrl = imageUrl,
    introduction = introduction,
    kakaoId = kakaoId,
    portfolio = portfolio
)
