package kr.co.knowledgerally.ui.coach

sealed interface CoachUiState {

    object Failure : CoachUiState

    object Loading : CoachUiState

    object Empty : CoachUiState

    data class Success(
        val matchingClasses: List<CoachLessonModel.Matching>,
        val scheduledClasses: List<CoachLessonModel.Scheduled>,
        val completedClasses: List<CoachLessonModel.Completed>,
    ) : CoachUiState
}
