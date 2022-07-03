package kr.co.knowledgerally.ui.player

import kr.co.knowledgerally.domain.model.Lesson
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

sealed interface PlayerLessonModel {
    val lessonId: String
    val lessonTitle: String
    val thumbnailUrl: String?
    val coachName: String
    val startTime: LocalDateTime
    val runningTime: Long

    data class Matching(
        override val lessonId: String,
        override val lessonTitle: String,
        override val thumbnailUrl: String?,
        override val coachName: String,
        override val startTime: LocalDateTime,
        override val runningTime: Long
    ) : PlayerLessonModel

    data class Scheduled(
        override val lessonId: String,
        override val lessonTitle: String,
        override val thumbnailUrl: String?,
        override val coachName: String,
        override val startTime: LocalDateTime,
        override val runningTime: Long,
        val coachKakaoId: String
    ) : PlayerLessonModel

    data class Completed(
        override val lessonId: String,
        override val lessonTitle: String,
        override val thumbnailUrl: String?,
        override val coachName: String,
        override val startTime: LocalDateTime,
        override val runningTime: Long,
        val isReviewed: Boolean
    ) : PlayerLessonModel
}

fun Lesson.toPlayerPresentation() =
    when (type) {
        Lesson.Type.Matching -> {
            PlayerLessonModel.Matching(
                lessonId = id.toString(),
                lessonTitle = title,
                thumbnailUrl = thumbnailUrl,
                coachName = coachName,
                startTime = startAt,
                runningTime = ChronoUnit.HOURS.between(endAt, startAt)
            )
        }
        Lesson.Type.Scheduled -> {
            PlayerLessonModel.Scheduled(
                lessonId = id.toString(),
                lessonTitle = title,
                thumbnailUrl = thumbnailUrl,
                coachName = coachName,
                coachKakaoId = coachKakaoId,
                startTime = startAt,
                runningTime = ChronoUnit.HOURS.between(endAt, startAt)
            )
        }
        Lesson.Type.Completed -> {
            PlayerLessonModel.Completed(
                lessonId = id.toString(),
                lessonTitle = title,
                thumbnailUrl = thumbnailUrl,
                coachName = coachName,
                startTime = startAt,
                runningTime = ChronoUnit.HOURS.between(endAt, startAt),
                isReviewed = isReviewed
            )
        }
    }
