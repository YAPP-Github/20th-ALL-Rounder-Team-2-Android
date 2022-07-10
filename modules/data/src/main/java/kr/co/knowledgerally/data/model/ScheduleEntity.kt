package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.Schedule
import java.time.LocalDateTime

data class ScheduleEntity(
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
)

internal fun Schedule.toData() = ScheduleEntity(
    startAt = startAt,
    endAt = endAt,
)

internal fun ScheduleEntity.toDomain() = Schedule(
    startAt = startAt,
    endAt = endAt,
)
