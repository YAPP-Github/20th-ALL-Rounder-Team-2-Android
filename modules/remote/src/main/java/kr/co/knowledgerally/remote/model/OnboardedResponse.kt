package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class OnboardedResponse(
    @SerializedName("data")
    val data: Data
) {

    data class Data(
        @SerializedName("isOnboard")
        val isOnboarded: Boolean
    )
}
