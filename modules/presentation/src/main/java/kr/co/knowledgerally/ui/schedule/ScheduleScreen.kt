package kr.co.knowledgerally.ui.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.AddScheduleButton
import kr.co.knowledgerally.ui.component.KnowllyTopAppBar
import kr.co.knowledgerally.ui.component.NavigationType
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun ScheduleScreen(
    viewModel: ScheduleViewModel,
    navigateUp: () -> Unit,
    showDatePicker: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        KnowllyTopAppBar(
            navigationType = NavigationType.Close,
            onNavigationClick = navigateUp
        )
        ScheduleContent(
            showDatePicker = showDatePicker,
        )
    }
}

@Composable
private fun ScheduleContent(
    showDatePicker: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val modifier = Modifier.padding(horizontal = 24.dp)

        Text(
            text = stringResource(id = R.string.schedule_title),
            modifier = modifier.padding(top = 12.dp),
            style = KnowllyTheme.typography.headline3,
        )

        Text(
            text = stringResource(id = R.string.schedule_subtitle),
            modifier = modifier.padding(top = 40.dp),
            style = KnowllyTheme.typography.subtitle1,
        )

        Text(
            text = stringResource(id = R.string.schedule_description),
            modifier = modifier.padding(top = 4.dp),
            style = KnowllyTheme.typography.body2,
            color = KnowllyTheme.colors.gray8F,
        )

        AddScheduleButton(showDatePicker, modifier.padding(top = 40.dp))
    }
}
