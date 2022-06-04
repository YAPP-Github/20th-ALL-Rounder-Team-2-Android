package kr.co.knowledgerally.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun ContainedBadge(
    modifier: Modifier = Modifier,
    text: String,
    contentColor: Color,
    backgroundColor: Color,
) {
    Text(
        text = text,
        style = KnowllyTheme.typography.button2,
        color = contentColor,
        modifier = modifier
            .background(color = backgroundColor, RoundedCornerShape(20.dp))
            .padding(vertical = 4.dp, horizontal = 8.dp),
    )
}
