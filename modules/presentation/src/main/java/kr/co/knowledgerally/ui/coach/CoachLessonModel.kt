package kr.co.knowledgerally.ui.coach

import kr.co.knowledgerally.domain.model.Applicant
import kr.co.knowledgerally.domain.model.Lesson

sealed interface CoachLessonModel {

    data class Matching(
        val lessonId: String,
        val lessonTitle: String,
        val thumbnailUrl: String?,
        val applicants: List<Applicant>,
    ) : CoachLessonModel

    data class Scheduled(
        val lessonId: String,
        val lessonTitle: String,
        val thumbnailUrl: String?,
        val playerName: String,
        val playerKakaoId: String,
        val startTime: String,
        val runningTime: String
    ) : CoachLessonModel

    data class Completed(
        val lessonId: String,
        val lessonTitle: String,
        val thumbnailUrl: String?,
        val playerName: String,
        val startTime: String,
        val runningTime: String,
        val isReviewed: Boolean
    ) : CoachLessonModel
}

fun Lesson.toCoachPresentation() =
    when (type) {
        Lesson.Type.Matching -> {
            CoachLessonModel.Matching(
                lessonId = id.toString(),
                lessonTitle = title,
                thumbnailUrl = thumbnailUrl,
                applicants = applicants!!
            )
        }
        Lesson.Type.Scheduled -> {
            CoachLessonModel.Scheduled(
                lessonId = id.toString(),
                lessonTitle = title,
                thumbnailUrl = thumbnailUrl,
                playerName = playerName!!,
                playerKakaoId = playerKakaoId!!,
                startTime = startAt.toString(),
                runningTime = "3시간" // LocalDateTime 계산 관련 함수가 거의 대부분 API 26 이상을 필요
            )
        }
        Lesson.Type.Completed -> {
            CoachLessonModel.Completed(
                lessonId = id.toString(),
                lessonTitle = title,
                thumbnailUrl = thumbnailUrl,
                playerName = playerName!!,
                startTime = startAt.toString(),
                runningTime = "3시간",
                isReviewed = isReviewed
            )
        }
    }
