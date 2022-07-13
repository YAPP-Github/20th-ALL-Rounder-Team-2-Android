package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class ImageRequest(
    @SerializedName("id")
    val id: Long,
)
