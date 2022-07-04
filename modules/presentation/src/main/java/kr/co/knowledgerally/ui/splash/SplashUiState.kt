package kr.co.knowledgerally.ui.splash

sealed class SplashUiState {
    object Unspecified : SplashUiState()
    object AlreadyLoggedIn : SplashUiState()
    object NeedToOnboard : SplashUiState()
    object NeedToLogin : SplashUiState()
}
