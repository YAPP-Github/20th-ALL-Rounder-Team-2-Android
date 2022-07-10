package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.ApplicantEntity
import kr.co.knowledgerally.data.model.ScheduleEntity
import java.time.LocalDateTime

data class FormResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("content")
    val content: String,
    @SerializedName("lecture")
    val lecture: LectureResponseLegacy,
    @SerializedName("user")
    val user: UserResponse,
    @SerializedName("state")
    val state: State,
    @SerializedName("expirationDate")
    val expirationDate: LocalDateTime
) {

    enum class State {
        @SerializedName("REQUEST")
        Request,

        @SerializedName("ACCEPT")
        Accept,

        @SerializedName("REJECT")
        Reject
    }
}

internal fun FormResponse.toData() = ApplicantEntity(
    id = id,
    name = user.username,
    content = content,
    imageUrl = user.imageUrl,
    schedule = ScheduleEntity(lecture.startAt, lecture.endAt),
)
