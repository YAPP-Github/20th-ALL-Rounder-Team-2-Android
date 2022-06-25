package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.User

data class UserEntity(
    val id: String,
    val profile: ProfileEntity,
    val ballCount: Int,
    val pushActive: Boolean,
)

fun UserEntity.toDomain() = User(
    id = id,
    profile = profile.toDomain(),
    ballCount = ballCount,
    pushActive = pushActive,
)
