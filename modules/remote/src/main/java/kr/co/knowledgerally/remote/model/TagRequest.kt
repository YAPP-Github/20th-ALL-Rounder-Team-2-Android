package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class TagRequest(
    @SerializedName("content")
    val content: String,
)
