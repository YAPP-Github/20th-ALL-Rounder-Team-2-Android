package kr.co.knowledgerally.ui.mypage

sealed interface MyPageUiState {
    object Loading : MyPageUiState

    data class Success(
        val notificationEnabled: Boolean,
        val versionName: String,
        val userName: String,
        val isCoach: Boolean,
        val remainingBallCount: Int,
    ) : MyPageUiState
}
