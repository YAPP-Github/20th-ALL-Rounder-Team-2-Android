package kr.co.knowledgerally.ui.splash

sealed class SplashUiState {
    object Unspecified : SplashUiState()
    data class AlreadyLoggedIn(val isOnboarded: Boolean) : SplashUiState()
    object NeedToLogin : SplashUiState()
}
