package kr.co.knowledgerally.ui.player

sealed class PlayerUiState {
    data class Success(
        val matchingLesson: List<LessonUiState.Matching>,
        val scheduledLesson: List<LessonUiState.Scheduled>,
        val completedLesson: List<LessonUiState.Completed>
    ) : PlayerUiState()

    object Failure : PlayerUiState()
    object Loading : PlayerUiState()
}
