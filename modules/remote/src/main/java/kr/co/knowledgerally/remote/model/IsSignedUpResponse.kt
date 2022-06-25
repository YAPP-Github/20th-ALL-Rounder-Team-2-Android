package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class IsSignedUpResponse(
    @SerializedName("data")
    val data: Data
) {

    data class Data(
        @SerializedName("exists")
        val isSignedUp: Boolean
    )
}
