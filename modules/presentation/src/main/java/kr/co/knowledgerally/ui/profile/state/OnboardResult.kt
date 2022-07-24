package kr.co.knowledgerally.ui.profile.state

sealed interface OnboardResult {
    object Created : OnboardResult
    object Modified : OnboardResult
}
