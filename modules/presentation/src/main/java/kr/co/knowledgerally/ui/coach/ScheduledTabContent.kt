package kr.co.knowledgerally.ui.coach

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun ScheduledTabContent(
    scheduledList: List<ClassUiState.Scheduled>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        CoachTitle(text = stringResource(R.string.coach_scheduled_title))
        CoachBanner(
            text = stringResource(id = R.string.coach_scheduled_banner),
            modifier = Modifier.padding(top = 10.dp),
        )

        if (scheduledList.isEmpty()) {
            EmptyItem(modifier = Modifier.padding(top = 24.dp))
        } else {
            CoachDivider(Modifier.padding(top = 24.dp))
        }
    }
}

@Composable
private fun EmptyItem(modifier: Modifier = Modifier) {
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
                text = stringResource(id = R.string.coach_scheduled_empty),
                style = KnowllyTheme.typography.body1,
                color = KnowllyTheme.colors.gray6B,
                textAlign = TextAlign.Center,
            )
        }
    }
}
