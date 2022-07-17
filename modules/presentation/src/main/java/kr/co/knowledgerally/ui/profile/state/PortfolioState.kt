package kr.co.knowledgerally.ui.profile.state

import androidx.compose.runtime.Stable

@Stable
class PortfolioState(initialText: String = "") : TextFieldState(
    initialText = initialText,
    validator = { true }
)
