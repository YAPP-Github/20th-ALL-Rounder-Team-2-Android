package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class PlayerLectureResponseWrapper(
    @SerializedName("data")
    val data: List<Data>
) {

    data class Data(
        @SerializedName("lecture")
        val lecture: LectureResponse,
        @SerializedName("lectureInfo")
        val lectureInfo: LectureInfoResponse,
    )
}
