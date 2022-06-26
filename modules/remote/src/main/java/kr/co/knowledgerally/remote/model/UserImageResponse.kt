package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class UserImageResponse(
    @SerializedName("userImgUrl")
    val userImageUrl: String?,
)
