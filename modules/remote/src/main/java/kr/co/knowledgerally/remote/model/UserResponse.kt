package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.ProfileEntity
import kr.co.knowledgerally.data.model.UserEntity

data class UserResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("username")
    val username: String,
    @SerializedName("intro")
    val introduction: String,
    @SerializedName("kakaoId")
    val kakaoId: String,
    @SerializedName("portfolio")
    val portfolio: String?,
    @SerializedName("ballCnt")
    val ballCount: Int,
    @SerializedName("coach")
    val coach: Boolean,
    @SerializedName("userImgUrl")
    val imageUrl: String?
)

fun UserResponse.toData() = UserEntity(
    id = id,
    profile = ProfileEntity(
        username = username,
        introduction = introduction,
        kakaoId = kakaoId,
        portfolio = portfolio ?: "",
        imageUrl = imageUrl
    ),
    ballCount = ballCount,
    coach = coach,
)
