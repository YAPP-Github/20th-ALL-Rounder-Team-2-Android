package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.JwtTokenEntity

data class JwtTokenResponse(
    @SerializedName("knowllyAccessToken")
    val accessToken: String,
    @SerializedName("knowllyRefreshToken")
    val refreshToken: String,
) {

    fun toData() = JwtTokenEntity(
        accessToken = accessToken,
        refreshToken = refreshToken,
    )
}
