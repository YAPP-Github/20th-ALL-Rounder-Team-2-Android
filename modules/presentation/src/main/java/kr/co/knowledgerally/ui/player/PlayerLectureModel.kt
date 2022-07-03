package kr.co.knowledgerally.ui.player

import kr.co.knowledgerally.domain.model.Lecture
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

sealed interface PlayerLectureModel {
    val lectureId: String
    val lectureTitle: String
    val thumbnailUrl: String?
    val coachName: String
    val startTime: LocalDateTime
    val runningTime: Long

    data class Matching(
        override val lectureId: String,
        override val lectureTitle: String,
        override val thumbnailUrl: String?,
        override val coachName: String,
        override val startTime: LocalDateTime,
        override val runningTime: Long
    ) : PlayerLectureModel

    data class Scheduled(
        override val lectureId: String,
        override val lectureTitle: String,
        override val thumbnailUrl: String?,
        override val coachName: String,
        override val startTime: LocalDateTime,
        override val runningTime: Long,
        val coachKakaoId: String
    ) : PlayerLectureModel

    data class Completed(
        override val lectureId: String,
        override val lectureTitle: String,
        override val thumbnailUrl: String?,
        override val coachName: String,
        override val startTime: LocalDateTime,
        override val runningTime: Long,
        val isReviewed: Boolean
    ) : PlayerLectureModel
}

fun Lecture.toPlayerPresentation() =
    when (type) {
        Lecture.Type.Matching -> {
            PlayerLectureModel.Matching(
                lectureId = id.toString(),
                lectureTitle = title,
                thumbnailUrl = thumbnailUrl,
                coachName = coachName,
                startTime = startAt,
                runningTime = ChronoUnit.HOURS.between(endAt, startAt)
            )
        }
        Lecture.Type.Scheduled -> {
            PlayerLectureModel.Scheduled(
                lectureId = id.toString(),
                lectureTitle = title,
                thumbnailUrl = thumbnailUrl,
                coachName = coachName,
                coachKakaoId = coachKakaoId,
                startTime = startAt,
                runningTime = ChronoUnit.HOURS.between(endAt, startAt)
            )
        }
        Lecture.Type.Completed -> {
            PlayerLectureModel.Completed(
                lectureId = id.toString(),
                lectureTitle = title,
                thumbnailUrl = thumbnailUrl,
                coachName = coachName,
                startTime = startAt,
                runningTime = ChronoUnit.HOURS.between(endAt, startAt),
                isReviewed = isReviewed
            )
        }
    }
