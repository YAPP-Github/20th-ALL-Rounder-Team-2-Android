package kr.co.knowledgerally.ui.coach

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun CoachTitle(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(top = 24.dp, start = 24.dp),
        style = KnowllyTheme.typography.headline4,
    )
}
