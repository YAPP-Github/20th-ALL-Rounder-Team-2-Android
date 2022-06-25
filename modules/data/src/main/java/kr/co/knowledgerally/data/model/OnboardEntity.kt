package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.Onboard

data class OnboardEntity(
    val username: String,
    val intro: String,
    val kakaoId: String,
    val portfolio: String?,
    val imageUriString: String?,
)

fun Onboard.toData() = OnboardEntity(
    username = username,
    intro = intro,
    kakaoId = kakaoId,
    portfolio = portfolio,
    imageUriString = imageUriString
)
