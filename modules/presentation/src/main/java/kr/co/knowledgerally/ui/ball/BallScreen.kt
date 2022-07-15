package kr.co.knowledgerally.ui.ball

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.domain.model.BallHistory
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.HorizontalSpacer
import kr.co.knowledgerally.ui.component.KnowllyTopAppBar
import kr.co.knowledgerally.ui.component.Loading
import kr.co.knowledgerally.ui.component.VerticalSpacer
import kr.co.knowledgerally.ui.theme.KnowllyTheme
import java.time.LocalDate
import kotlin.math.abs

@Composable
fun BallScreen(
    viewModel: BallViewModel,
    navigateUp: () -> Unit,
    navigateToGuide: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    BallScreen(
        uiState = uiState,
        navigateUp = navigateUp,
        navigateToGuide = navigateToGuide
    )
}

@Composable
fun BallScreen(
    uiState: BallUiState,
    navigateUp: () -> Unit,
    navigateToGuide: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        KnowllyTopAppBar(onNavigationClick = navigateUp)
        Column(
            modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 0.dp)
        ) {
            MyBall(uiState = uiState)
            VerticalSpacer(height = 24.dp)
            BallBanner(onClick = navigateToGuide)
            VerticalSpacer(height = 48.dp)
            BallHistoryContent(uiState = uiState)
        }
    }
}

@Composable
fun MyBall(uiState: BallUiState) {
    val ballCount = if (uiState is BallUiState.Success) uiState.ballCount else 0

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.ball_my), style = KnowllyTheme.typography.headline2)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_ball_large),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            HorizontalSpacer(width = 8.dp)
            Text(
                text = ballCount.toString() + stringResource(id = R.string.ball_count),
                style = KnowllyTheme.typography.headline4
            )
        }
    }
}

@Composable
fun BallBanner(
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = KnowllyTheme.colors.grayF7,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(start = 12.dp, top = 12.dp, end = 16.dp, bottom = 12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info, // google icons
                    contentDescription = null,
                    tint = KnowllyTheme.colors.gray8F,
                    modifier = Modifier
                        .size(16.dp)
                )
                HorizontalSpacer(width = 4.dp)
                Text(
                    text = stringResource(R.string.ball_banner),
                    style = KnowllyTheme.typography.body2,
                    color = KnowllyTheme.colors.gray6B
                )
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight, // google icons
                contentDescription = null,
                tint = KnowllyTheme.colors.gray8F,
                modifier = Modifier
                    .size(16.dp)

            )
        }
    }
}

@Composable
fun BallHistoryContent(uiState: BallUiState) {
    when (uiState) {
        is BallUiState.Success -> {
            BallHistoryList(histories = uiState.histories)
        }
        BallUiState.Loading -> {
            Loading()
        }
        BallUiState.Failure -> {}
    }
}

@Composable
fun BallHistoryList(
    histories: List<BallHistory>
) {
    LazyColumn {
        item { Divider(color = KnowllyTheme.colors.grayEF) }
        items(histories) {
            BallHistoryListItem(history = it)
            Divider(color = KnowllyTheme.colors.grayEF)
        }
    }
}

@Composable
fun BallHistoryListItem(
    history: BallHistory
) {
    val sign = if (history.count > 0) "+" else "-"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = history.title, style = KnowllyTheme.typography.subtitle2)
            VerticalSpacer(height = 4.dp)
            Text(
                text = "${history.date} | ${history.content}",
                style = KnowllyTheme.typography.body1,
                color = KnowllyTheme.colors.gray8F
            )
        }
        Text(
            text = "$sign ${abs(history.count)}" + stringResource(id = R.string.ball_count),
            style = KnowllyTheme.typography.subtitle1
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BallScreenPreview() {
    val tempBallHistoryList = listOf(
        BallHistory(
            title = "클래스 운영",
            content = "프랑스어",
            date = LocalDate.now(),
            count = 1
        ),
        BallHistory(
            title = "클래스 수강",
            content = "요리 원데이 클래스",
            date = LocalDate.now(),
            count = -1
        ),
        BallHistory(
            title = "첫 가입 축하 볼",
            content = "첫 가입 축하 볼",
            date = LocalDate.now(),
            count = 1
        )
    )

    KnowllyTheme {
        BallScreen(
            uiState = BallUiState.Loading,
            navigateUp = {},
            navigateToGuide = {}
        )
    }
}
