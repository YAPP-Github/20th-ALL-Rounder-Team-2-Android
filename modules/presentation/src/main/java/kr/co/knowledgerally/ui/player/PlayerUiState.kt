package kr.co.knowledgerally.ui.player

import kr.co.knowledgerally.ui.model.PlayerLectureModel

sealed interface PlayerUiState {

    data class Success(
        val matchingLectures: List<PlayerLectureModel.Matching>,
        val scheduledLectures: List<PlayerLectureModel.Scheduled>,
        val completedLectures: List<PlayerLectureModel.Completed>
    ) : PlayerUiState

    object Failure : PlayerUiState

    object Loading : PlayerUiState
}
