package kr.co.knowledgerally.ui.profile.state

import androidx.compose.runtime.Stable

@Stable
class IntroductionState(initialText: String = "") : TextFieldState(
    initialText = initialText,
    validator = { it.length in (MIN_LENGTH..MAX_LENGTH) }
) {

    companion object {
        const val MIN_LENGTH = 10
        const val MAX_LENGTH = 100
    }
}
