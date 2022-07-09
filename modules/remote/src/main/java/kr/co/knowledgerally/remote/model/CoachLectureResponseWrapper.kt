package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class CoachLectureResponseWrapper(
    @SerializedName("data")
    val lectures: List<CoachLectureResponse>
)