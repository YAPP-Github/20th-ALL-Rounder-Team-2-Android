package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.LectureInfoLegacy
import java.time.LocalDateTime

data class LectureInfoEntityLegacy(
    val id: Long,
    val title: String,
    val imageUrls: List<String>,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime
)

internal fun LectureInfoEntityLegacy.toDomain() = LectureInfoLegacy(
    id = id,
    title = title,
    imageUrls = imageUrls,
    startAt = startAt,
    endAt = endAt
)
