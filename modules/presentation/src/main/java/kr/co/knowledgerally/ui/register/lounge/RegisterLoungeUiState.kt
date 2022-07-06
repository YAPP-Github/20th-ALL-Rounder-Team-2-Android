package kr.co.knowledgerally.ui.register.lounge

sealed interface RegisterLoungeUiState {

    object Loading : RegisterLoungeUiState

    object NoLecture : RegisterLoungeUiState

    data class Lectures(val lectures: String) : RegisterLoungeUiState
}
