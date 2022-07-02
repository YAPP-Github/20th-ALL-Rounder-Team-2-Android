package kr.co.knowledgerally.ui.coach

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.toast.Toaster
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.Banner
import kr.co.knowledgerally.ui.component.DashBanner
import kr.co.knowledgerally.ui.component.KnowllyOutlinedButton
import kr.co.knowledgerally.ui.component.RoundRect
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun ScheduledTabContent(
    scheduledList: List<CoachLessonModel.Scheduled>,
    scrollState: ScrollState = rememberScrollState(),
) {
    val clipboardManager = LocalClipboardManager.current
    val copyTo: (String) -> Unit = {
        clipboardManager.setText(AnnotatedString(it))
        Toaster.show(R.string.copied_kakao_id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
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
                    CoachDivider(modifier = Modifier.padding(top = 24.dp))
                }
                ScheduledItem(scheduled = scheduled, copyToClipboard = copyTo::invoke)
                CoachDivider()
            }
        }
    }
}

@Composable
private fun ScheduledItem(
    scheduled: CoachLessonModel.Scheduled,
    copyToClipboard: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 20.dp)
    ) {
        Box(modifier = Modifier.height(IntrinsicSize.Max)) {
            RoundRect(radius = 8.dp, width = 4.dp)
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
