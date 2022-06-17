package kr.co.knowledgerally.ui.ball

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
                .fillMaxWidth()
                .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 0.dp)
        ) {
            Text(text = "볼 지급 및 차감 안내", style = KnowllyTheme.typography.headline3)
            VerticalSpacer(height = 40.dp)
            BallBannerList(caseText = "지급되는 경우") {
                BallBannerListItem(text = "첫 가입 축하", changedBallCount = 1)
                BallBannerListItem(text = "첫 클래스 등록 축하", changedBallCount = 1)
                BallBannerListItem(
                    text = "클래스 진행 1회",
                    subtext = "*클래스 종료 직후 지급",
                    changedBallCount = 1
                )
            }
            VerticalSpacer(height = 24.dp)
            BallBannerList(caseText = "차감되는 경우") {
                BallBannerListItem(
                    text = "클래스 수강 1회",
                    subtext = "*매칭 성공 직후 차감",
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
        Divider(modifier = Modifier.padding(vertical = 12.dp))
        VerticalSpacer(height = 4.dp)
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxWidth(),
            content = caseList
        )
        Divider(modifier = Modifier.padding(vertical = 16.dp))
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
                text = "$sign ${abs(changedBallCount)}개",
                style = KnowllyTheme.typography.subtitle2
            )
        }
        if (subtext != null) {
            VerticalSpacer(height = 4.dp)
            Text(
                text = subtext,
                style = KnowllyTheme.typography.caption,
                color = KnowllyTheme.colors.gray6B
            )
        }
    }
}

@Preview
@Composable
private fun KnowllyBallBannerPreview() {
    KnowllyTheme {
        BallBannerScreen()
    }
}
