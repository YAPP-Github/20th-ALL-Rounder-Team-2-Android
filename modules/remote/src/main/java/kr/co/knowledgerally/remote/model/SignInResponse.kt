package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("data")
    val jwtToken: JwtTokenResponse
)