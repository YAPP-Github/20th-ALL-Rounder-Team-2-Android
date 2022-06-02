package kr.co.knowledgerally.ui.ball

import kr.co.knowledgerally.ui.model.BallHistoryModel

sealed class BallUiState {
    object Loading : BallUiState()
    data class Success(val histories: List<BallHistoryModel>) : BallUiState()
    object Empty : BallUiState()
    object Failure : BallUiState()
}
