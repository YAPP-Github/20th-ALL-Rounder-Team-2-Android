package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.User

data class UserEntity(
    val id: Long,
    val profile: ProfileEntity,
    val ballCount: Int,
    val pushActive: Boolean,
    val coach: Boolean,
)

fun UserEntity.toDomain() = User(
    id = id,
    profile = profile.toDomain(),
    ballCount = ballCount,
    pushActive = pushActive,
    coach = coach,
)
