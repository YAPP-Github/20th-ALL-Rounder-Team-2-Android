package kr.co.knowledgerally.ui.coach

sealed interface CoachUiState {
    object Loading : CoachUiState

    object Empty : CoachUiState

    // TODO : 클래스 목록 받아오기
    object Running : CoachUiState
}
