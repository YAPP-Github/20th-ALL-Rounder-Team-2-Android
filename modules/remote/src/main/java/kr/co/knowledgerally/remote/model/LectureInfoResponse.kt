package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class LectureInfoResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("topic")
    val title: String,
    @SerializedName("introduce")
    val introduction: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("coach")
    val coach: CoachResponse,
    @SerializedName("lectureImages")
    val images: List<LectureImageResponse>
)
