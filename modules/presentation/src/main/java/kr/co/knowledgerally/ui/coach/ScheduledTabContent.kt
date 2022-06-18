package kr.co.knowledgerally.ui.coach

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.Banner
import kr.co.knowledgerally.ui.component.DashBanner
import kr.co.knowledgerally.ui.component.KnowllyOutlinedButton
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun ScheduledTabContent(
    scheduledList: List<ClassUiState.Scheduled>,
) {
    val context = LocalContext.current
    val toastMessage = stringResource(id = R.string.copied_kakao_id)
    val clipboardManager = LocalClipboardManager.current
    val copyTo: (String) -> Unit = {
        clipboardManager.setText(AnnotatedString(it))
        Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        CoachTitle(text = stringResource(R.string.coach_scheduled_title))
        Banner(
            text = stringResource(id = R.string.coach_scheduled_banner),
            modifier = Modifier.padding(top = 10.dp),
        )

        if (scheduledList.isEmpty()) {
            DashBanner(
                text = stringResource(id = R.string.coach_scheduled_empty),
                modifier = Modifier.padding(top = 24.dp)
            )
        } else {
            scheduledList.forEachIndexed { index, scheduled ->
                if (index == 0) {
                    CoachDivider()
                }
                ScheduledItem(scheduled = scheduled, copyToClipboard = copyTo::invoke)
                CoachDivider()
            }
        }
    }
}

@Composable
private fun ScheduledItem(
    scheduled: ClassUiState.Scheduled,
    copyToClipboard: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 20.dp)
    ) {
        Box(modifier = Modifier.height(IntrinsicSize.Max)) {
            val color = KnowllyTheme.colors.primary
            Canvas(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(4.dp)
            ) {
                drawRoundRect(
                    color = color,
                    cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx())
                )
            }
            Column(modifier = Modifier.padding(start = 14.dp, top = 4.dp, bottom = 4.dp)) {
                Text(text = "프랑스어", style = KnowllyTheme.typography.subtitle2)
                Text(
                    text = "유지민님",
                    style = KnowllyTheme.typography.body1,
                    modifier = Modifier.padding(top = 2.dp)
                )
                Text(
                    text = "2022년 5월 4일 (화)",
                    modifier = Modifier.padding(top = 6.dp),
                    style = KnowllyTheme.typography.body2,
                    color = KnowllyTheme.colors.gray6B
                )
                Text(
                    text = "오후 6:00 (3시간 수업)",
                    style = KnowllyTheme.typography.body2,
                    color = KnowllyTheme.colors.gray6B
                )
            }
        }
        KnowllyOutlinedButton(
            text = stringResource(id = R.string.copy_kakao_id),
            onClick = { copyToClipboard("") },
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .height(40.dp)
        )
    }
}
