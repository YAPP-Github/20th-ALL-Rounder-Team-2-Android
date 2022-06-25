package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.OnboardEntity

data class OnboardRequest(
    @SerializedName("username")
    val username: String,
    @SerializedName("intro")
    val intro: String,
    @SerializedName("kakaoId")
    val kakaoId: String,
    @SerializedName("portfolio")
    val portfolio: String?,
)

fun OnboardEntity.toRemote() = OnboardRequest(
    username = username,
    intro = intro,
    kakaoId = kakaoId,
    portfolio = portfolio
)
