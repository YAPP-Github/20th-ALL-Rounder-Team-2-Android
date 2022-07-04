package kr.co.knowledgerally.domain.model

data class Profile(
    val username: String,
    val imageUrl: String?,
    val introduction: String,
    val portfolio: String,
)
