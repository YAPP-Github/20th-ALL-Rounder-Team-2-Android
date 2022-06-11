package kr.co.knowledgerally.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun PageIndicator(
    value: Int,
    modifier: Modifier = Modifier,
    isActive: Boolean = false,
) {
    val color = if (isActive) KnowllyTheme.colors.gray00 else KnowllyTheme.colors.grayCC
    val borderColor = if (isActive) KnowllyTheme.colors.gray6B else KnowllyTheme.colors.grayDD
    Surface(
        modifier = modifier.size(20.dp),
        shape = CircleShape,
        color = color,
        border = BorderStroke(2.dp, borderColor)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = value.coerceAtLeast(0).toString(),
                style = KnowllyTheme.typography.button2,
                color = KnowllyTheme.colors.grayFF,
            )
        }
    }
}

@Preview(widthDp = 20, heightDp = 20)
@Composable
private fun PageIndicatorPreviewActive() {
    KnowllyTheme {
        PageIndicator(value = 1, isActive = true)
    }
}

@Preview(widthDp = 20, heightDp = 20)
@Composable
private fun PageIndicatorPreviewInactive() {
    KnowllyTheme {
        PageIndicator(value = 2, isActive = false)
    }
}
