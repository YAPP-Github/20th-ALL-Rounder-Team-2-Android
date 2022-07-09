package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class UserResponseWrapper(
    @SerializedName("data")
    val data: Data,
) {

    data class Data(
        @SerializedName("user")
        val user: UserResponse
    )
}
