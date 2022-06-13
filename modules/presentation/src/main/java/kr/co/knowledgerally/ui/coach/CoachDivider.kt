package kr.co.knowledgerally.ui.coach

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun CoachDivider(modifier: Modifier = Modifier) {
    Divider(modifier, KnowllyTheme.colors.grayEF)
}
