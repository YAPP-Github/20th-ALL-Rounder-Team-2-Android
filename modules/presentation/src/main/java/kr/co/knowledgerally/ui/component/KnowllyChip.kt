package kr.co.knowledgerally.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun KnowllyChip(
    text: String,
    active: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = if (active) {
            KnowllyTheme.colors.primary
        } else {
            Color.Unspecified
        },
        shape = RoundedCornerShape(40.dp),
        border = if (active) {
            null
        } else {
            BorderStroke(1.dp, KnowllyTheme.colors.gray8F)
        }
    ) {
        Box(
            modifier = Modifier
                .clickable { onClick() }
                .padding(vertical = 4.dp, horizontal = 10.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                style = KnowllyTheme.typography.subtitle4,
                color = if (active) {
                    KnowllyTheme.colors.grayFF
                } else {
                    KnowllyTheme.colors.gray8F
                }
            )
        }
    }
}
