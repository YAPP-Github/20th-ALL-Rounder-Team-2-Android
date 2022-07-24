package kr.co.knowledgerally.ui.profile

import kr.co.knowledgerally.domain.model.User
import kr.co.knowledgerally.ui.profile.state.OnboardResult

data class ProfileUiState(
    val isLoading: Boolean = false,
    val isModifying: Boolean = false,
    val user: User? = null,
    val result: OnboardResult? = null,
)
