package kr.co.knowledgerally.ui.ball

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.HorizontalSpacer
import kr.co.knowledgerally.ui.component.KnowllyTopAppBar
import kr.co.knowledgerally.ui.component.VerticalSpacer
import kr.co.knowledgerally.ui.theme.KnowllyTheme
import kotlin.math.abs

@Composable
fun BallBannerScreen() {
    Column {
        KnowllyTopAppBar()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 0.dp)
        ) {
            Text(
                text = stringResource(id = R.string.ball_guide),
                style = KnowllyTheme.typography.headline3
            )
            VerticalSpacer(height = 40.dp)
            BallBannerList(caseText = stringResource(id = R.string.ball_guide_receive)) {
                BallBannerListItem(
                    text = stringResource(id = R.string.ball_guide_receive_case_01),
                    changedBallCount = 1
                )
                BallBannerListItem(
                    text = stringResource(id = R.string.ball_guide_receive_case_02),
                    changedBallCount = 1
                )
                BallBannerListItem(
                    text = stringResource(id = R.string.ball_guide_receive_case_03),
                    subtext = stringResource(id = R.string.ball_guide_receive_case_03_sub),
                    changedBallCount = 1
                )
            }
            VerticalSpacer(height = 24.dp)
            BallBannerList(caseText = stringResource(id = R.string.ball_guide_toss)) {
                BallBannerListItem(
                    text = stringResource(id = R.string.ball_guide_toss_case_01),
                    subtext = stringResource(id = R.string.ball_guide_toss_case_01_sub),
                    changedBallCount = -1
                )
            }
        }
    }
}

@Composable
fun BallBannerList(
    caseText: String,
    caseList: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_ball),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(16.dp)
            )
            HorizontalSpacer(width = 8.dp)
            Text(text = caseText, style = KnowllyTheme.typography.subtitle1)
        }
        BallBannerDivider(modifier = Modifier.padding(vertical = 12.dp))
        VerticalSpacer(height = 4.dp)
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxWidth(),
            content = caseList
        )
        BallBannerDivider(modifier = Modifier.padding(vertical = 16.dp))
    }
}

@Composable
fun BallBannerListItem(
    text: String,
    subtext: String? = null,
    changedBallCount: Int
) {
    val sign = if (changedBallCount > 0) "+" else "-"

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = text, style = KnowllyTheme.typography.subtitle2)
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "$sign ${abs(changedBallCount)}" + stringResource(id = R.string.ball_count),
                style = KnowllyTheme.typography.subtitle2
            )
        }
        if (subtext != null) {
            VerticalSpacer(height = 8.dp)
            Text(
                text = subtext,
                style = KnowllyTheme.typography.caption,
                color = KnowllyTheme.colors.gray6B
            )
        }
    }
}

@Composable
fun BallBannerDivider(modifier: Modifier = Modifier) {
    Divider(color = KnowllyTheme.colors.grayEF, modifier = modifier)
}

@Preview(showBackground = true)
@Composable
private fun KnowllyBallBannerPreview() {
    KnowllyTheme {
        BallBannerScreen()
    }
}
