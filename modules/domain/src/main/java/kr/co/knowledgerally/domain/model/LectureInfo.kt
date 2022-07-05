package kr.co.knowledgerally.domain.model

import java.time.LocalDateTime

data class LectureInfo(
    val id: Int,
    val title: String,
    val imageUrls: List<String>,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime
)
