package kr.co.knowledgerally.ui.player

import kr.co.knowledgerally.domain.model.Lesson

sealed interface LessonUiState {

    data class Matching(
        val lessonId: String,
        val lessonTitle: String,
        val startTime: String,
        val runningTime: String
    ) : LessonUiState

    data class Scheduled(
        val lessonId: String,
        val lessonTitle: String,
        val coachName: String,
        val coachKakaoId: String,
        val startTime: String,
        val runningTime: String
    ) : LessonUiState

    data class Completed(
        val lessonId: String,
        val lessonTitle: String,
        val coachName: String,
        val startTime: String,
        val runningTime: String,
        val isReviewed: Boolean
    ) : LessonUiState
}

fun Lesson.toPlayerPresentation() =
    when (type) {
        Lesson.Type.Matching -> {
            LessonUiState.Matching(
                lessonId = id.toString(),
                lessonTitle = title,
                startTime = startAt.toString(),
                runningTime = "3시간"
            )
        }
        Lesson.Type.Scheduled -> {
            LessonUiState.Scheduled(
                lessonId = id.toString(),
                lessonTitle = title,
                coachName = coachName,
                coachKakaoId = coachKakaoId,
                startTime = startAt.toString(),
                runningTime = "3시간"
            )
        }
        Lesson.Type.Completed -> {
            LessonUiState.Completed(
                lessonId = id.toString(),
                lessonTitle = title,
                coachName = coachName,
                startTime = startAt.toString(),
                runningTime = "3시간",
                isReviewed = isReviewed
            )
        }
    }
