package kr.co.knowledgerally.ui.coach

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun CoachTitle(text: String) {
    Text(
        text = text,
        style = KnowllyTheme.typography.headline4,
    )
}
