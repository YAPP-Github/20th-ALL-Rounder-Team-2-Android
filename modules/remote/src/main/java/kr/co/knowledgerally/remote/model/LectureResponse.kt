package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.LectureEntity
import kr.co.knowledgerally.data.model.ScheduleEntity
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
    val state: LectureStateResponse,
    @SerializedName("forms")
    val forms: List<FormResponse>,
    @SerializedName("matechedUser")
    val matchedUser: UserResponse,
)

internal fun LectureResponse.toData() = when (state) {
    LectureStateResponse.Onboard -> LectureEntity.Onboard(
        id = id,
        schedule = ScheduleEntity(startAt, endAt),
        applicants = forms.map { it.toData() }
    )
    LectureStateResponse.Ongoing -> LectureEntity.Ongoing(
        id = id,
        schedule = ScheduleEntity(startAt, endAt),
        player = matchedUser.toData()
    )
    LectureStateResponse.Done -> LectureEntity.Done(
        id = id,
        schedule = ScheduleEntity(startAt, endAt),
        player = matchedUser.toData(),
        isReviewed = isReviewed,
    )
}
