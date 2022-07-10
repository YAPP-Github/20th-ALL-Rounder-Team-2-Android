package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class LectureInfoResponseWrapper(
    @SerializedName("data")
    val data: LectureInfoResponseLegacy
)
