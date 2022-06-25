package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class UserExistsResponse(
    @SerializedName("data")
    val data: Data
) {

    data class Data(
        @SerializedName("exists")
        val exists: Boolean
    )
}
