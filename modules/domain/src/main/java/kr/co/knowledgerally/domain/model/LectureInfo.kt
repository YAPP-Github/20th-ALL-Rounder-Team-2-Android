package kr.co.knowledgerally.domain.model

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class LectureInfo(
    val id: Long,
    val title: String,
    val imageUrls: List<String>,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime
) {
    val runningTime: Long = ChronoUnit.HOURS.between(endAt, startAt)
}
