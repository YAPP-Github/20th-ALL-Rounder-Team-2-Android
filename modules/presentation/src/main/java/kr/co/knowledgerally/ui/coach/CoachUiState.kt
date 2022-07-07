package kr.co.knowledgerally.ui.coach

sealed interface CoachUiState {

    object Failure : CoachUiState

    object Loading : CoachUiState

    object Empty : CoachUiState

    data class Success(
        val matchingLectures: List<CoachLectureUiState.Matching>,
        val scheduledLectures: List<CoachLectureUiState.Scheduled>,
        val completedLectures: List<CoachLectureUiState.Completed>,
    ) : CoachUiState
}
