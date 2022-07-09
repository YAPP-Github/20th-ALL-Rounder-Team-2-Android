package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class LectureImageResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("lectureImgUrl")
    val imageUrl: String
)
