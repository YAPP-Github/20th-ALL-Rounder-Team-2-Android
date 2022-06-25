package kr.co.knowledgerally.ui.signup

sealed interface SignUpResult {

    object Ready : SignUpResult

    object Success : SignUpResult
}
