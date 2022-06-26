package kr.co.knowledgerally.ui.login

sealed class LoginState {
    object NotLoggedIn : LoginState()

    data class NeedToSignUp(
        val providerAccessToken: String,
    ) : LoginState()

    object NeedToOnboard : LoginState()

    object Success : LoginState()
}
