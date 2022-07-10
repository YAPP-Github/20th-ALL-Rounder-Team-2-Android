package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.ScheduleEntity
import java.time.LocalDateTime

data class ScheduleRequest(
    @SerializedName("startAt")
    val startAt: LocalDateTime,
    @SerializedName("endAt")
    val endAt: LocalDateTime,
)

internal fun ScheduleEntity.toRemote() = ScheduleRequest(
    startAt = startAt,
    endAt = endAt,
)
