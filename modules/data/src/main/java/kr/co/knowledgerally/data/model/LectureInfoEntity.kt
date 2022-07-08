package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.LectureInfo
import java.time.LocalDateTime

data class LectureInfoEntity(
    val id: Long,
    val title: String,
    val imageUrls: List<String>,
    val startAt: String,
    val endAt: String
)

internal fun LectureInfoEntity.toDomain() = LectureInfo(
    id = id,
    title = title,
    imageUrls = imageUrls,
    startAt = LocalDateTime.parse(startAt),
    endAt = LocalDateTime.parse(endAt)
)
