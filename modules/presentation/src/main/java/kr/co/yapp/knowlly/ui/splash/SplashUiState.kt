package kr.co.yapp.knowlly.ui.splash

sealed class SplashUiState {
    object Checking : SplashUiState()
    object AlreadyLoggedIn : SplashUiState()
    object NeedToLogin : SplashUiState()
}
