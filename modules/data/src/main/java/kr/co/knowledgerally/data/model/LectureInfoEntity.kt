package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.LectureInfo
import java.time.LocalDateTime

data class LectureInfoEntity(
    val id: Long,
    val title: String,
    val imageUrls: List<String>,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime
)

internal fun LectureInfoEntity.toDomain() = LectureInfo(
    id = id,
    title = title,
    imageUrls = imageUrls,
    startAt = startAt,
    endAt = endAt
)
