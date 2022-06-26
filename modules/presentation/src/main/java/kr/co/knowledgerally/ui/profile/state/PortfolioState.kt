package kr.co.knowledgerally.ui.profile.state

import androidx.compose.runtime.Stable

@Stable
class PortfolioState : TextFieldState(
    initialText = "",
    validator = { true }
)
