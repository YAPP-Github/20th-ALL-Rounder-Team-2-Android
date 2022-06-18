package kr.co.knowledgerally.ui.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
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

        Surface(
            shape = RoundedCornerShape(10.dp),
            onClick = showDatePicker,
            modifier = modifier
                .padding(top = 40.dp)
                .fillMaxWidth()
                .height(52.dp),
            color = KnowllyTheme.colors.primaryLight,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null,
                    tint = KnowllyTheme.colors.primaryDark
                )
                Text(
                    text = stringResource(id = R.string.register_schedule_add_schedule),
                    modifier = Modifier.padding(start = 2.dp),
                    style = KnowllyTheme.typography.button1,
                    color = KnowllyTheme.colors.primaryDark,
                )
            }
        }
    }
}
