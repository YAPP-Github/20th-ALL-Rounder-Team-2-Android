package kr.co.knowledgerally.ui.model

import kr.co.knowledgerally.domain.model.LectureInfo
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class LectureInfoModel(
    val id: String,
    val title: String,
    val imageUrls: List<String>,
    val startAt: LocalDateTime,
    val runningTime: Long
)

fun LectureInfo.toPresentation() = LectureInfoModel(
    id = id.toString(),
    title = title,
    imageUrls = imageUrls,
    startAt = startAt,
    runningTime = ChronoUnit.HOURS.between(endAt, startAt)
)