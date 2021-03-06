package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class OnboardedResponse(
    @SerializedName("data")
    val data: Data
) {

    data class Data(
        @SerializedName("onboard")
        val isOnboarded: Boolean
    )
}
