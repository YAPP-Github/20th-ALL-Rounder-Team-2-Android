package kr.co.knowledgerally.remote.model

import kr.co.knowledgerally.data.model.ProfileEntity
import kr.co.knowledgerally.data.model.UserEntity

data class UserResponse(
    val id: String,
    val username: String,
    val introduction: String,
    val portfolio: String?,
    val ballCount: Int,
    val pushActive: Boolean,
)

fun UserResponse.toData(imageUrl: String?) = UserEntity(
    id = id,
    profile = ProfileEntity(
        username = username,
        introduction = introduction,
        portfolio = portfolio ?: "",
        imageUrl = imageUrl
    ),
    ballCount = ballCount,
    pushActive = pushActive
)
