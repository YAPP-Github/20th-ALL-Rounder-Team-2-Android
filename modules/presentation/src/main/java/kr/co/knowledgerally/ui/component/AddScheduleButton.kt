package kr.co.knowledgerally.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun AddScheduleButton(
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
