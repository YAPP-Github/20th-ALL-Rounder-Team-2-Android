package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class PlayerLectureResponseWrapper(
    @SerializedName("data")
    val lectures: List<PlayerLectureResponse>
)
