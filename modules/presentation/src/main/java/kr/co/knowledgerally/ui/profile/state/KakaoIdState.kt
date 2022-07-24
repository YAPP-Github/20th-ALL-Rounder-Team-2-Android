package kr.co.knowledgerally.ui.profile.state

import androidx.compose.runtime.Stable

@Stable
class KakaoIdState(
    initialText: String = "",
) : TextFieldState(
    initialText = initialText,
    validator = { it.isNotBlank() }
)
