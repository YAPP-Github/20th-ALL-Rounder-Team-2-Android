package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.ProfileEntity
import kr.co.knowledgerally.data.model.UserEntity

data class UserResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("intro")
    val introduction: String,
    @SerializedName("portfolio")
    val portfolio: String?,
    @SerializedName("ballCnt")
    val ballCount: Int,
    @SerializedName("pushActive")
    val pushActive: Boolean,
    @SerializedName("coach")
    val coach: Boolean,
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
    pushActive = pushActive,
    coach = coach,
)
