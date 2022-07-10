package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class PushRequest(
    @SerializedName("pushActive")
    val pushActive: Boolean
)
