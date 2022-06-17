package kr.co.knowledgerally.ui.applicant

import kr.co.knowledgerally.domain.model.Applicant

sealed interface ApplicantUiState {
    object Loading : ApplicantUiState

    object Empty : ApplicantUiState

    data class Applicants(
        val applicants: List<Applicant>,
    ) : ApplicantUiState
}
