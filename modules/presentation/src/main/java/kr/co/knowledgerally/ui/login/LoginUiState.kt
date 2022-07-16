package kr.co.knowledgerally.ui.login

import kr.co.knowledgerally.domain.model.LoginResult

data class LoginUiState(
    val isLoading: Boolean = false,
    val result: LoginResult? = null
)
