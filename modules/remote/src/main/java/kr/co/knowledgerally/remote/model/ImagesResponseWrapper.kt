package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class ImagesResponseWrapper(
    @SerializedName("data")
    val data: List<LectureImageResponse>
)
