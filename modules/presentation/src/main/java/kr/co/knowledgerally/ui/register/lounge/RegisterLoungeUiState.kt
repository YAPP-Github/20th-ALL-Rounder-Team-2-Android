package kr.co.knowledgerally.ui.register.lounge

import kr.co.knowledgerally.domain.model.LectureLegacy

sealed interface RegisterLoungeUiState {

    object Loading : RegisterLoungeUiState

    object NoLecture : RegisterLoungeUiState

    data class Lectures(
        val lectures: List<LectureLegacy.Onboard>
    ) : RegisterLoungeUiState
}
