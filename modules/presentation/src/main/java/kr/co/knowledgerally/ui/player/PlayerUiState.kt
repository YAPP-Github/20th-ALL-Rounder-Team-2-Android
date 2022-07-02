package kr.co.knowledgerally.ui.player

sealed interface PlayerUiState {

    data class Success(
        val matchingLessons: List<PlayerLessonModel.Matching>,
        val scheduledLessons: List<PlayerLessonModel.Scheduled>,
        val completedLessons: List<PlayerLessonModel.Completed>
    ) : PlayerUiState

    object Failure : PlayerUiState

    object Loading : PlayerUiState
}
