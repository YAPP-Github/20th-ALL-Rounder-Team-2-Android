package kr.co.knowledgerally.domain.model

data class Onboard(
    val username: String,
    val intro: String,
    val kakaoId: String,
    val portfolio: String?,
    val imageUriString: String?,
)
