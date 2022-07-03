package kr.co.knowledgerally.ui.coach

import kr.co.knowledgerally.domain.model.Applicant
import kr.co.knowledgerally.domain.model.Lesson
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

sealed interface CoachLessonModel {

    data class Matching(
        val lessonId: String,
        val lessonTitle: String,
        val applicants: List<Applicant>,
    ) : CoachLessonModel

    data class Scheduled(
        val lessonId: String,
        val lessonTitle: String,
        val playerName: String,
        val playerKakaoId: String,
        val startTime: LocalDateTime,
        val runningTime: Long
    ) : CoachLessonModel

    data class Completed(
        val lessonId: String,
        val lessonTitle: String,
        val playerName: String,
        val startTime: LocalDateTime,
        val runningTime: Long,
        val isReviewed: Boolean
    ) : CoachLessonModel
}

fun Lesson.toCoachPresentation() =
    when (type) {
        Lesson.Type.Matching -> {
            CoachLessonModel.Matching(
                lessonId = id.toString(),
                lessonTitle = title,
                applicants = applicants!!
            )
        }
        Lesson.Type.Scheduled -> {
            CoachLessonModel.Scheduled(
                lessonId = id.toString(),
                lessonTitle = title,
                playerName = playerName!!,
                playerKakaoId = playerKakaoId!!,
                startTime = startAt,
                runningTime = ChronoUnit.HOURS.between(endAt, startAt)
            )
        }
        Lesson.Type.Completed -> {
            CoachLessonModel.Completed(
                lessonId = id.toString(),
                lessonTitle = title,
                playerName = playerName!!,
                startTime = startAt,
                runningTime = ChronoUnit.HOURS.between(endAt, startAt),
                isReviewed = isReviewed
            )
        }
    }
