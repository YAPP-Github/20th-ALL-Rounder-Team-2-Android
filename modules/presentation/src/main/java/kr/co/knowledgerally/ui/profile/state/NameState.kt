package kr.co.knowledgerally.ui.profile.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Stable
class NameState(initialText: String) : TextFieldState(
    initialText = initialText,
    validator = {
        it.isNotBlank() && it.length < MAX_LENGTH
    }
) {

    companion object {
        const val MAX_LENGTH = 10
    }
}

@Composable
fun rememberNameState(
    initialText: String = "",
) = remember { NameState(initialText = initialText) }
