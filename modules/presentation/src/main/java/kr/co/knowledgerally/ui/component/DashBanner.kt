package kr.co.knowledgerally.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun DashBanner(text: String, modifier: Modifier = Modifier) {
    val brush = KnowllyTheme.colors.grayEF
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val radius = 10.dp.toPx()
            val dashWidth = 2.dp.toPx()
            drawRoundRect(
                color = brush,
                cornerRadius = CornerRadius(x = radius, y = radius),
                style = Stroke(
                    width = 2.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(dashWidth, dashWidth)
                    )
                )
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp, horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = KnowllyTheme.typography.body1,
                color = KnowllyTheme.colors.gray6B,
                textAlign = TextAlign.Center,
            )
        }
    }
}
