package kr.co.knowledgerally.domain.model

data class User(
    val id: String,
    val profile: Profile,
    val ballCount: Int,
    val pushActive: Boolean,
)
