package kr.co.knowledgerally.ui.mypage

import kr.co.knowledgerally.domain.model.User

data class MyPageUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val versionName: String = "",
    val isSignOut: Boolean = false,
)
