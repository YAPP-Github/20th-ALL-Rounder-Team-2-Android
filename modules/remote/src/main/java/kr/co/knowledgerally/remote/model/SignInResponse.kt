package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("data")
    val data: Data
) {

    data class Data(
        @SerializedName("jwtToken")
        val jwtToken: JwtTokenResponse,
    )
}
