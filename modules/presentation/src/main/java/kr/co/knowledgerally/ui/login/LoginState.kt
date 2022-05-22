package kr.co.knowledgerally.ui.login

sealed class LoginState {
    object NotLoggedIn : LoginState()
    object Success : LoginState()
    object Failure : LoginState()
}