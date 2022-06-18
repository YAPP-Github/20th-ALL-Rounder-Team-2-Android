package kr.co.knowledgerally.ui.player

sealed interface LessonUiState {

    data class Matching(
        val lessonId: Long
    ) : LessonUiState

    data class Scheduled(
        val lessonId: Long,
        val kakaoId: String
    ) : LessonUiState

    data class Completed(
        val lessonId: Long,
        val isReviewed: Boolean
    ) : LessonUiState
}
