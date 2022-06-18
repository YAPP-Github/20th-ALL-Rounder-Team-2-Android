package kr.co.knowledgerally.ui.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.component.KnowllyTopAppBar
import kr.co.knowledgerally.ui.component.PageIndicator
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun ScheduleScreen(
    navigateUp: () -> Unit,
    navigateToSchedule: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {

            KnowllyTopAppBar(onNavigationClick = navigateUp)

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

            Surface(
                shape = RoundedCornerShape(10.dp),
                onClick = navigateToSchedule,
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

        KnowllyContainedButton(
            text = stringResource(id = R.string.do_register),
            onClick = { /* TODO */ },
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            enabled = false,
        )
    }
}

@Preview(widthDp = 360, showBackground = true)
@Composable
private fun ScheduleScreenPreview() {
    KnowllyTheme {
        ScheduleScreen(
            navigateUp = { },
            navigateToSchedule = { },
        )
    }
}
