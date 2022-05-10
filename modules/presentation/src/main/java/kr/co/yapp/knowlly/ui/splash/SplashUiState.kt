package kr.co.yapp.knowlly.ui.splash

sealed class SplashUiState {
    object Unspecified : SplashUiState()
    object AlreadyLoggedIn : SplashUiState()
    object NeedToLogin : SplashUiState()
}
