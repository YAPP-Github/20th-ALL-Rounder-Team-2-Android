package kr.co.knowledgerally.ui.component

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun KnowllyDivider(
    modifier: Modifier = Modifier
) {
    Divider(modifier, color = KnowllyTheme.colors.grayEF)
}