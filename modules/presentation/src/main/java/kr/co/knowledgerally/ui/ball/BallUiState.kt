package kr.co.knowledgerally.ui.ball

import kr.co.knowledgerally.domain.model.BallHistory

sealed class BallUiState {
    object Loading : BallUiState()
    data class Success(val ballCount: Int, val histories: List<BallHistory>) : BallUiState()
    object Failure : BallUiState()
}
