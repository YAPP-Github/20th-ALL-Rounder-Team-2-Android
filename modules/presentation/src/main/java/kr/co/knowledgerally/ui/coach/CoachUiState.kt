package kr.co.knowledgerally.ui.coach

sealed interface CoachUiState {

    object Loading : CoachUiState

    object Empty : CoachUiState

    data class Success(
        val matchingClasses: List<ClassUiState.Matching>,
        val scheduledClasses: List<ClassUiState.Scheduled>,
        val completedClasses: List<ClassUiState.Completed>,
    ) : CoachUiState
}
