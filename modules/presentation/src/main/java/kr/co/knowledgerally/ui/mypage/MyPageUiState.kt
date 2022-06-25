package kr.co.knowledgerally.ui.mypage

import kr.co.knowledgerally.domain.model.User

sealed interface MyPageUiState {
    object Loading : MyPageUiState

    data class Success(
        val user: User,
        val versionName: String,
    ) : MyPageUiState
}
