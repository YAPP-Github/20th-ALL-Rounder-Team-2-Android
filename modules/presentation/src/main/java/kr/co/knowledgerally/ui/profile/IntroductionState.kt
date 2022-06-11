package kr.co.knowledgerally.ui.profile

import androidx.compose.runtime.Stable

@Stable
class IntroductionState : TextFieldState(
    initialText = "",
    validator = { it.length in (MIN_LENGTH..MAX_LENGTH) }
) {

    companion object {
        const val MIN_LENGTH = 10
        const val MAX_LENGTH = 100
    }
}
