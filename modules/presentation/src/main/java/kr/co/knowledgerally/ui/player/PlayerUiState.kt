package kr.co.knowledgerally.ui.player

sealed interface PlayerUiState {

    data class Success(
        val matchingLectures: List<PlayerLectureUiState.Matching>,
        val scheduledLectures: List<PlayerLectureUiState.Scheduled>,
        val completedLectures: List<PlayerLectureUiState.Completed>
    ) : PlayerUiState

    object Failure : PlayerUiState

    object Loading : PlayerUiState
}
