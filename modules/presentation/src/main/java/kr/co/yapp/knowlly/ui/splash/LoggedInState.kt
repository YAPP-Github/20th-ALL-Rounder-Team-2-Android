package kr.co.yapp.knowlly.ui.splash

sealed class LoggedInState {

    object AlreadyLoggedIn : LoggedInState()
    object NeedToLogin : LoggedInState()
}
