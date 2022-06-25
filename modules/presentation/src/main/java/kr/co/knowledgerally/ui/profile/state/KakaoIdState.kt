package kr.co.knowledgerally.ui.profile.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Stable
class KakaoIdState(
    initialText: String,
) : TextFieldState(
    initialText = initialText,
    validator = { it.isNotBlank() }
)

@Composable
fun rememberKakaoIdState(
    initialText: String = "",
): KakaoIdState = remember { KakaoIdState(initialText) }
