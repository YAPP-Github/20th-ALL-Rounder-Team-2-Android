package kr.co.knowledgerally.ui.register.schedule

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kr.co.knowledgerally.domain.model.Schedule
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.AddScheduleButton
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.component.KnowllyTopAppBar
import kr.co.knowledgerally.ui.component.Loading
import kr.co.knowledgerally.ui.component.PageIndicator
import kr.co.knowledgerally.ui.schedule.ScheduleActivity
import kr.co.knowledgerally.ui.schedule.ScheduleResult
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun RegisterScheduleScreen(
    viewModel: RegisterScheduleViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onResult: () -> Unit,
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            if (it.resultCode == Activity.RESULT_OK) {
                ScheduleResult.from(it.data)
                    ?.toDomain()
                    ?.let(viewModel::addSchedule)
            }
        }
    )
    val uiState by viewModel.uiState.collectAsState()
    RegisterScheduleScreen(
        schedules = uiState.schedules,
        onScheduleRemove = viewModel::removeSchedule,
        onBackClick = onBackClick,
        navigateToSchedule = { launcher.launch(ScheduleActivity.getIntent(context)) },
        onRegister = { viewModel.register() },
    )

    if (uiState.isLoading) {
        Loading()
    }

    uiState.result?.let {
        val currentOnResult by rememberUpdatedState(onResult)
        LaunchedEffect(uiState) { currentOnResult() }
    }
}

@Composable
fun RegisterScheduleScreen(
    schedules: List<Schedule>,
    onScheduleRemove: (Schedule) -> Unit,
    onBackClick: () -> Unit,
    navigateToSchedule: () -> Unit,
    onRegister: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {

            KnowllyTopAppBar(onNavigationClick = onBackClick)

            val modifier = Modifier.padding(horizontal = 24.dp)
            Row(modifier = modifier.padding(top = 12.dp)) {
                PageIndicator(value = 1)
                PageIndicator(
                    value = 2,
                    modifier = Modifier.padding(start = 8.dp),
                    isActive = true,
                )
            }
            Text(
                text = stringResource(id = R.string.register_schedule_title),
                modifier = modifier.padding(top = 24.dp),
                style = KnowllyTheme.typography.headline3,
            )

            Text(
                text = stringResource(id = R.string.register_schedule_description),
                modifier = modifier.padding(top = 16.dp),
                style = KnowllyTheme.typography.body1,
                color = KnowllyTheme.colors.gray8F,
            )

            AddScheduleButton(navigateToSchedule, modifier.padding(top = 40.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(
                    start = 24.dp,
                    top = 40.dp,
                    end = 24.dp,
                    bottom = 104.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(
                    items = schedules,
                    key = { it.toString() },
                ) { schedule ->
                    RegisterScheduleItem(
                        schedule = schedule,
                        onScheduleRemove = onScheduleRemove,
                    )
                }
            }
        }

        KnowllyContainedButton(
            text = stringResource(id = R.string.do_register),
            onClick = onRegister,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            enabled = schedules.isNotEmpty(),
        )
    }
}

@Preview(widthDp = 360, showBackground = true)
@Composable
private fun RegisterScheduleScreenPreview() {
    KnowllyTheme {
        RegisterScheduleScreen(
            schedules = listOf(),
            onScheduleRemove = { },
            onBackClick = { },
            navigateToSchedule = { },
            onRegister = { },
        )
    }
}
