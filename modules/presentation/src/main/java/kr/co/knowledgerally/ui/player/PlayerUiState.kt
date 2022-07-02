package kr.co.knowledgerally.ui.player

sealed interface PlayerUiState {

    data class Success(
        val matchingLessons: List<LessonUiState.Matching>,
        val scheduledLessons: List<LessonUiState.Scheduled>,
        val completedLessons: List<LessonUiState.Completed>
    ) : PlayerUiState

    object Failure : PlayerUiState
    
    object Loading : PlayerUiState
}
