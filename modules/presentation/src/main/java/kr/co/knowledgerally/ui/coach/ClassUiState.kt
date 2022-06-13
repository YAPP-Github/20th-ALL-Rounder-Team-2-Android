package kr.co.knowledgerally.ui.coach

import kr.co.knowledgerally.domain.model.Applicant

sealed interface ClassUiState {

    data class Matching(
        val classId: String,
        val className: String,
        val applicants: List<Applicant>,
    ) : ClassUiState

    object Scheduled : ClassUiState

    object Completed : ClassUiState
}
