package kr.co.knowledgerally.ui.register.lounge

import kr.co.knowledgerally.domain.model.Lecture

sealed interface RegisterLoungeUiState {

    object Loading : RegisterLoungeUiState

    object NoLecture : RegisterLoungeUiState

    data class Lectures(
        val lectures: List<Lecture.Onboard>
    ) : RegisterLoungeUiState
}
