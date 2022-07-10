package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.LectureStateEntity

enum class LectureStateResponse {
    @SerializedName("ON_BOARD")
    Onboard,

    @SerializedName("ON_GOING")
    Ongoing,

    @SerializedName("DONE")
    Done
}

internal fun LectureStateEntity.toRemote() = when (this) {
    LectureStateEntity.Onboard -> LectureStateResponse.Onboard
    LectureStateEntity.Ongoing -> LectureStateResponse.Ongoing
    LectureStateEntity.Done -> LectureStateResponse.Done
}
