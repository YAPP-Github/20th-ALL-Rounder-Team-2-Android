package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class LectureResponse(
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
        @SerializedName("ON_BOARD") Onboard,
        @SerializedName("ON_GOING") Ongoing,
        @SerializedName("DONE") Done
    }
}
