package kr.co.knowledgerally.ui.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.domain.model.TimePeriod
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyChip
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.component.KnowllyTopAppBar
import kr.co.knowledgerally.ui.component.NavigationType
import kr.co.knowledgerally.ui.theme.KnowllyTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ScheduleScreen(
    state: ScheduleState,
    navigateUp: () -> Unit,
    showDatePicker: () -> Unit,
    addSchedule: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        KnowllyTopAppBar(
            navigationType = NavigationType.Close,
            onNavigationClick = navigateUp
        )
        ScheduleContent(
            state = state,
            showDatePicker = showDatePicker,
            addSchedule = addSchedule,
        )
    }
}

@Composable
private fun ScheduleContent(
    state: ScheduleState,
    showDatePicker: () -> Unit,
    addSchedule: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 90.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ScheduleHeader(modifier = Modifier.padding(horizontal = 24.dp))

            DateButton(
                selectedDate = state.date,
                onClick = showDatePicker,
                modifier = Modifier.padding(start = 24.dp, top = 20.dp, end = 24.dp)
            )

            if (state.hasDate) {
                ClassTimeSelection(
                    state = state,
                    modifier = Modifier.padding(top = 60.dp)
                )
            }
        }

        KnowllyContainedButton(
            text = stringResource(id = R.string.do_add),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            enabled = state.canAdd,
            onClick = addSchedule
        )
    }
}

@Composable
private fun ScheduleHeader(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.schedule_title),
            modifier = Modifier.padding(top = 12.dp),
            style = KnowllyTheme.typography.headline3,
        )
        Text(
            text = stringResource(id = R.string.schedule_select_date),
            modifier = Modifier.padding(top = 40.dp),
            style = KnowllyTheme.typography.subtitle1,
        )
        Text(
            text = stringResource(id = R.string.schedule_select_date_description),
            modifier = Modifier.padding(top = 4.dp),
            style = KnowllyTheme.typography.body2,
            color = KnowllyTheme.colors.gray8F,
        )
    }
}

@Composable
private fun DateButton(
    selectedDate: LocalDate?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        color = KnowllyTheme.colors.primaryLight,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = if (selectedDate == null) {
                    stringResource(id = R.string.schedule_select_date)
                } else {
                    val pattern = stringResource(id = R.string.schedule_date_fmt)
                    DateTimeFormatter.ofPattern(pattern).format(selectedDate)
                },
                style = KnowllyTheme.typography.button1,
                color = KnowllyTheme.colors.primaryDark,
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_right),
                contentDescription = null,
                modifier = Modifier.padding(start = 2.dp),
                tint = KnowllyTheme.colors.primaryDark
            )
        }
    }
}

@Composable
private fun ClassTimeSelection(
    state: ScheduleState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ClassTimeSelectionDescription(Modifier.padding(horizontal = 24.dp))

        ScheduleDivider(Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp))

        // 클래스 시작 시간
        StartTimePicker(
            timePeriod = state.timePeriod,
            onTimePeriodChange = { state.timePeriod = it },
            hour = state.hour,
            onHourChange = { state.hour = it },
            minute = state.minute,
            onMinuteChange = { state.minute = it },
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        ScheduleDivider(Modifier.padding(horizontal = 24.dp))

        // 클래스 예상 소요 시간
        RunningTimePicker(
            runningTimes = state.runningTimes,
            selectedRunningTime = state.runningTime,
            onRunningTimeChange = { state.runningTime = it },
        )

        ScheduleDivider(Modifier.padding(horizontal = 24.dp))
    }
}

@Composable
private fun ClassTimeSelectionDescription(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.schedule_select_time),
            style = KnowllyTheme.typography.subtitle1,
        )
        Text(
            text = stringResource(id = R.string.schedule_select_time_description),
            modifier = Modifier.padding(top = 4.dp),
            style = KnowllyTheme.typography.body2,
            color = KnowllyTheme.colors.gray8F,
        )
    }
}

@Composable
private fun StartTimePicker(
    timePeriod: TimePeriod,
    onTimePeriodChange: (TimePeriod) -> Unit,
    hour: String,
    onHourChange: (String) -> Unit,
    minute: String,
    onMinuteChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 32.dp)
    ) {
        Text(
            text = stringResource(id = R.string.schedule_select_time_start_time),
            style = KnowllyTheme.typography.subtitle4,
            color = KnowllyTheme.colors.gray6B,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TimeTextField(
                range = TimeRange.Hours,
                value = hour,
                onValueChange = onHourChange
            )
            TimeColon(count = 2, modifier = Modifier.padding(horizontal = 6.dp))
            TimeTextField(
                range = TimeRange.Minutes,
                value = minute,
                onValueChange = onMinuteChange
            )
            TimePeriodText(
                timePeriod = TimePeriod.AM,
                selected = timePeriod == TimePeriod.AM,
                onClick = { onTimePeriodChange(TimePeriod.AM) },
                modifier = Modifier.padding(start = 28.dp),
            )
            TimeColon(count = 1, modifier = Modifier.padding(horizontal = 10.dp))
            TimePeriodText(
                timePeriod = TimePeriod.PM,
                selected = timePeriod == TimePeriod.PM,
                onClick = { onTimePeriodChange(TimePeriod.PM) },
            )
        }
    }
}

@Composable
private fun TimeTextField(
    range: TimeRange,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val textStyle = KnowllyTheme.typography.headline1.copy(
        fontFamily = FontFamily.Monospace
    )
    Box(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .background(
                shape = RoundedCornerShape(8.dp),
                color = KnowllyTheme.colors.grayEF,
            )
            .padding(vertical = 6.dp, horizontal = 12.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = { value ->
                if (value in range) {
                    onValueChange(value)
                }
            },
            textStyle = textStyle,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions { focusManager.clearFocus() },
            singleLine = true,
            decorationBox = { innerTextField ->
                Text(
                    text = "00",
                    modifier = Modifier.fillMaxWidth(),
                    style = textStyle,
                    color = if (value.isNotEmpty()) {
                        KnowllyTheme.colors.grayEF
                    } else {
                        KnowllyTheme.colors.grayCC
                    },
                )
                innerTextField()
            }
        )
    }
}

@Composable
private fun TimeColon(
    count: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        repeat(count) {
            Surface(
                shape = CircleShape,
                modifier = Modifier.size(6.dp),
                color = KnowllyTheme.colors.gray8F,
                content = { },
            )
        }
    }
}

@Composable
private fun TimePeriodText(
    timePeriod: TimePeriod,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = when (timePeriod) {
            TimePeriod.AM -> stringResource(id = R.string.am)
            TimePeriod.PM -> stringResource(id = R.string.pm)
        },
        modifier = modifier.clickable(
            onClick = onClick,
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
        ),
        style = KnowllyTheme.typography.headline4,
        color = if (selected) {
            KnowllyTheme.colors.gray00
        } else {
            KnowllyTheme.colors.grayCC
        }
    )
}

@Composable
private fun RunningTimePicker(
    runningTimes: List<Int>,
    selectedRunningTime: Int?,
    onRunningTimeChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(top = 24.dp, bottom = 32.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = R.string.schedule_select_duration),
            modifier = Modifier.padding(horizontal = 24.dp),
            style = KnowllyTheme.typography.subtitle4,
            color = KnowllyTheme.colors.gray6B,
        )
        LazyRow(
            modifier = Modifier.padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 24.dp),
        ) {
            items(
                items = runningTimes,
                key = { it }
            ) { runningTime ->
                KnowllyChip(
                    text = stringResource(id = R.string.schedule_select_duration_fmt, runningTime),
                    active = runningTime == selectedRunningTime,
                    onClick = { onRunningTimeChange(runningTime) },
                )
            }
        }
    }
}

@Composable
private fun ScheduleDivider(modifier: Modifier) {
    Divider(
        modifier = modifier.height(1.dp),
        color = KnowllyTheme.colors.grayEF
    )
}

@Preview(name = "선택시간 없는 경우", widthDp = 360, showBackground = true)
@Composable
fun ScheduleScreenPreview() {
    KnowllyTheme {
        ScheduleScreen(
            state = ScheduleState(null),
            navigateUp = { },
            showDatePicker = { },
            addSchedule = { },
        )
    }
}

@Preview(name = "2022년 7월 3일, 오전 11시 30분, 예상 소요시간 2시간", widthDp = 360, showBackground = true)
@Composable
fun ScheduleScreenPreviewSelected() {
    val state = ScheduleState(LocalDate.of(2022, 7, 3)).apply {
        hour = "11"
        minute = "30"
        timePeriod = TimePeriod.AM
        runningTime = 2
    }

    KnowllyTheme {
        ScheduleScreen(
            state = state,
            navigateUp = { },
            showDatePicker = { },
            addSchedule = { },
        )
    }
}
