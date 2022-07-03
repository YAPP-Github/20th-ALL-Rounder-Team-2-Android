package kr.co.knowledgerally.ui.coach

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.Banner
import kr.co.knowledgerally.ui.component.DashBanner
import kr.co.knowledgerally.ui.component.OutlinedBadge
import kr.co.knowledgerally.ui.component.RoundRect
import kr.co.knowledgerally.ui.theme.KnowllyTheme
import java.time.format.DateTimeFormatter

@Composable
fun CompletedTabContent(
    completedList: List<CoachLessonModel.Completed>,
    scrollState: ScrollState = rememberScrollState()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp)
    ) {
        CoachTitle(text = stringResource(R.string.coach_completed_title))
        Banner(
            text = stringResource(id = R.string.coach_completed_banner),
            modifier = Modifier.padding(top = 10.dp),
        )

        if (completedList.isEmpty()) {
            DashBanner(
                text = stringResource(id = R.string.coach_completed_empty),
                modifier = Modifier.padding(top = 24.dp)
            )
        } else {
            completedList.forEachIndexed { index, completed ->
                if (index == 0) {
                    CoachDivider(modifier = Modifier.padding(top = 24.dp))
                }
                CompletedItem(completed = completed)
                CoachDivider()
            }
        }
    }
}

@Composable
private fun CompletedItem(
    completed: CoachLessonModel.Completed,
) {
    Box(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 20.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        RoundRect(radius = 8.dp, width = 4.dp, color = KnowllyTheme.colors.grayCC)
        OutlinedBadge(
            text = stringResource(id = R.string.coach_completed_ball_saved),
            modifier = Modifier
                .padding(top = 4.dp)
                .align(Alignment.TopEnd),
            color = KnowllyTheme.colors.primaryDark,
            contentPadding = PaddingValues(start = 8.dp, top = 3.dp, end = 6.dp, bottom = 3.dp),
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 2.dp)
                        .size(14.dp),
                    tint = Color.Unspecified,
                )
            },
        )
        Column(modifier = Modifier.padding(start = 14.dp, top = 4.dp, bottom = 4.dp)) {
            Text(text = completed.lessonTitle, style = KnowllyTheme.typography.subtitle2)
            Text(
                text = completed.playerName,
                style = KnowllyTheme.typography.body1,
                modifier = Modifier.padding(top = 2.dp)
            )
            Text(
                text = completed.startTime.format(
                    DateTimeFormatter.ofPattern(stringResource(id = R.string.lecture_date_format))
                ),
                modifier = Modifier.padding(top = 6.dp),
                style = KnowllyTheme.typography.body2,
                color = KnowllyTheme.colors.gray6B
            )
            Text(
                text = completed.startTime.format(
                    DateTimeFormatter.ofPattern(stringResource(id = R.string.lecture_time_format))
                ) + " " + stringResource(
                    R.string.lecture_runningtime_format,
                    completed.runningTime
                ),
                style = KnowllyTheme.typography.body2,
                color = KnowllyTheme.colors.gray6B
            )
        }
    }
}

