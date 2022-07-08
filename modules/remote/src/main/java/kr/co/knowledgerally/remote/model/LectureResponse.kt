package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class LectureResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("reviewWritten")
    val isReviewed: Boolean,
    @SerializedName("startAt")
    val startAt: String,
    @SerializedName("endAt")
    val endAt: String,
    @SerializedName("state")
    val state: String // ON_BOARD, ON_GOING, DONE
)
