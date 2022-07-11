package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.LectureInfoEntity

data class LectureInfoResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("lectures")
    val lectures: List<LectureResponse>,
    @SerializedName("topic")
    val topic: String,
    @SerializedName("coach")
    val coach: CoachResponse,
    @SerializedName("lectureImages")
    val images: List<LectureImageResponse>,
    @SerializedName("category")
    val category: CategoryResponse,
)

fun LectureInfoResponse.toData(): LectureInfoEntity = LectureInfoEntity(
    id = id,
    lectures = lectures.map { it.toData() },
    topic = topic,
    coach = coach.user.toData(),
    imageUrls = images.map { it.imageUrl },
    category = category.toData(),
)
