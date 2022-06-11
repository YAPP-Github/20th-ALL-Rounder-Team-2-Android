package kr.co.knowledgerally.ui.mypage

sealed interface MyPageUiState {
    object Loading : MyPageUiState

    /**
     * TODO: User Model을 직접 받도록 개선
     */
    data class Success(
        val notificationEnabled: Boolean,
        val versionName: String,
        val userName: String,
        val isCoach: Boolean,
        val remainingBallCount: Int,
    ) : MyPageUiState
}
