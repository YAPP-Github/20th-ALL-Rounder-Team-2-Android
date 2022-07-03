package kr.co.knowledgerally.ui.coach

sealed interface CoachUiState {

    object Failure : CoachUiState

    object Loading : CoachUiState

    object Empty : CoachUiState

    data class Success(
        val matchingLectures: List<CoachLectureModel.Matching>,
        val scheduledLectures: List<CoachLectureModel.Scheduled>,
        val completedLectures: List<CoachLectureModel.Completed>,
    ) : CoachUiState
}
