package kr.co.knowledgerally.ui.profile.state

import androidx.compose.runtime.Stable

@Stable
class NameState(initialText: String = "") : TextFieldState(
    initialText = initialText,
    validator = {
        it.isNotBlank() && it.length <= MAX_LENGTH
    }
) {

    companion object {
        const val MAX_LENGTH = 10
    }
}
