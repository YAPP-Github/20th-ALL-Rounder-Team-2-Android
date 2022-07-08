package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class CoachResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("introduce")
    val intro: String,
    @SerializedName("user")
    val user: UserResponse
)
