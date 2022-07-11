package kr.co.knowledgerally.domain.model

data class User(
    val id: Long,
    val profile: Profile,
    val ballCount: Int,
    val pushActive: Boolean,
    val coach: Boolean,
)
