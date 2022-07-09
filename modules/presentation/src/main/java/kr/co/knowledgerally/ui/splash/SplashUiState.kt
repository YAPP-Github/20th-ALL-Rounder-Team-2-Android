package kr.co.knowledgerally.ui.splash

sealed interface SplashUiState {
    object Loading : SplashUiState

    object Tutorial : SplashUiState

    object AlreadyLoggedIn : SplashUiState

    object NeedToOnboard : SplashUiState

    object NeedToLogin : SplashUiState
}
