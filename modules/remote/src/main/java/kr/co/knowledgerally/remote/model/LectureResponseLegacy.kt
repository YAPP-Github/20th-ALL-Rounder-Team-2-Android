package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.LectureStateEntity
import java.time.LocalDateTime

data class LectureResponseLegacy(
    @SerializedName("id")
    val id: Long,
    @SerializedName("reviewWritten")
    val isReviewed: Boolean,
    @SerializedName("startAt")
    val startAt: LocalDateTime,
    @SerializedName("endAt")
    val endAt: LocalDateTime,
    @SerializedName("state")
    val state: State
) {

    enum class State {
        @SerializedName("ON_BOARD")
        Onboard,

        @SerializedName("ON_GOING")
        Ongoing,

        @SerializedName("DONE")
        Done
    }
}