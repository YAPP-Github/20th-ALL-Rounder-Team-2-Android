package kr.co.knowledgerally.ui.register.lounge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.ContainedBadge
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun RegisterLoungeItemHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 28.dp)
    ) {
        Text(
            text = stringResource(id = R.string.register_lounge_register_time),
            style = KnowllyTheme.typography.headline4
        )
        Text(
            text = stringResource(id = R.string.register_lounge_register_time_description),
            modifier = Modifier.padding(top = 4.dp),
            style = KnowllyTheme.typography.body1,
            color = KnowllyTheme.colors.gray8F,
        )
    }
}

@Composable
fun RegisterLoungeItem(
    navigateToSchedule: (Long) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row {

            Surface(
                modifier = Modifier.size(88.dp),
                color = KnowllyTheme.colors.grayEF,
                shape = RoundedCornerShape(8.dp)
            ) {
                AsyncImage(
                    model = null,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                )
            }

            Column(
                modifier = Modifier
                    .padding(start = 18.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "3D Maya 디자인 - 모델링 기초",
                    style = KnowllyTheme.typography.subtitle2,
                )
                Text(
                    text = "오늘만님",
                    modifier = Modifier.padding(top = 4.dp),
                    style = KnowllyTheme.typography.body2,
                    color = KnowllyTheme.colors.gray8F
                )

                ContainedBadge(
                    text = "디자인",
                    contentColor = KnowllyTheme.colors.secondaryIndigo,
                    backgroundColor = KnowllyTheme.colors.secondaryIndigoLight,
                    modifier = Modifier.padding(top = 20.dp),
                )
            }
        }

        Surface(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .height(40.dp),
            color = KnowllyTheme.colors.primaryLight,
            shape = RoundedCornerShape(18.dp),
            onClick = { navigateToSchedule(1) }
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    modifier = Modifier.size(16.dp),
                    tint = KnowllyTheme.colors.primaryDark,
                    contentDescription = null,
                )
                Text(
                    text = stringResource(id = R.string.register_lounge_register_time_button),
                    modifier = Modifier.padding(start = 2.dp),
                    style = KnowllyTheme.typography.button1,
                    color = KnowllyTheme.colors.primaryDark
                )
            }
        }
    }
}

@Composable
fun RegisterLoungeItemDivider() {
    Divider(
        modifier = Modifier
            .padding(top = 24.dp, bottom = 16.dp)
            .height(1.dp),
        color = KnowllyTheme.colors.grayEF
    )
}
