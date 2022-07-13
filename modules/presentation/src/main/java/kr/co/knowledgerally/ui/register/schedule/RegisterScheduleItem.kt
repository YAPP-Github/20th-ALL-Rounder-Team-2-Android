package kr.co.knowledgerally.ui.register.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.domain.model.Schedule
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.theme.KnowllyTheme
import java.time.format.DateTimeFormatter

@Composable
fun RegisterScheduleItem(
    schedule: Schedule,
    onScheduleRemove: (Schedule) -> Unit,
) {
    val title = schedule.dateText()
    val description = schedule.timeText()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = KnowllyTheme.colors.grayCC,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Column(
            modifier = Modifier.padding(
                start = 16.dp,
                top = 12.dp,
                end = 12.dp,
                bottom = 12.dp
            )
        ) {
            Text(
                text = title,
                style = KnowllyTheme.typography.subtitle4,
                color = KnowllyTheme.colors.gray44,
            )
            Text(
                text = description,
                modifier = Modifier.padding(top = 2.dp),
                style = KnowllyTheme.typography.body2,
                color = KnowllyTheme.colors.gray6B
            )
        }
        Box(
            modifier = Modifier
                .padding(end = 12.dp)
                .size(26.dp)
                .clip(CircleShape)
                .align(Alignment.CenterEnd)
                .background(KnowllyTheme.colors.grayEF, CircleShape)
                .clickable { onScheduleRemove(schedule) },
            contentAlignment = Alignment.Center,
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_clear),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                )
            },
        )
    }
}

@Composable
private fun Schedule.dateText(): String {
    val pattern = stringResource(id = R.string.register_schedule_date_fmt)
    return DateTimeFormatter.ofPattern(pattern).format(startAt)
}

@Composable
private fun Schedule.timeText(): String = buildString {
    append(
        DateTimeFormatter
            .ofPattern(stringResource(id = R.string.register_schedule_time_fmt))
            .format(startAt)
    )
    append(" ")
    append(stringResource(id = R.string.lecture_runningtime_format, runningTime))
}
