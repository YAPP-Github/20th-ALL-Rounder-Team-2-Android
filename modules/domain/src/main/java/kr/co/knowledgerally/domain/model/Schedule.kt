package kr.co.knowledgerally.domain.model

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class Schedule(
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
) {
    val runningTime: Long = ChronoUnit.HOURS.between(startAt, endAt)
}
