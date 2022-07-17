package kr.co.knowledgerally.ui.profile.state

sealed interface CompleteState {
    object Created : CompleteState
    object Modified : CompleteState
    object Waiting : CompleteState
}
