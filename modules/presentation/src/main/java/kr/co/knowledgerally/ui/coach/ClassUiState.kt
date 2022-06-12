package kr.co.knowledgerally.ui.coach

sealed interface ClassUiState {

    object Matching : ClassUiState

    object Scheduled : ClassUiState

    object Completed : ClassUiState
}
