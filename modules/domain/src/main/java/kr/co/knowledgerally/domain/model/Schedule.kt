package kr.co.knowledgerally.domain.model

import java.time.LocalDateTime

data class Schedule(
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
)
