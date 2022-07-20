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
    @SerializedName("user")
    val user: UserResponse,
    @SerializedName("startAt")
    val startAt: LocalDateTime,
    @SerializedName("endAt")
    val endAt: LocalDateTime,
)

internal fun FormResponse.toData() = ApplicantEntity(
    id = id,
    name = user.username,
    content = content,
    imageUrl = user.imageUrl,
    schedule = ScheduleEntity(startAt, endAt),
)
