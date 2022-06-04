package kr.co.knowledgerally.ui.ball

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.HorizontalSpacer
import kr.co.knowledgerally.ui.component.VerticalSpacer
import kr.co.knowledgerally.ui.model.BallCountModel
import kr.co.knowledgerally.ui.model.BallHistoryModel
import kr.co.knowledgerally.ui.theme.KnowllyTheme
import kotlin.math.abs

@Composable
fun BallScreen(viewModel: BallViewModel) {
    val ball by viewModel.ball.collectAsState()
    val state by viewModel.state.collectAsState()

    BallScreen(ball = ball, state = state)
}

@Composable
fun BallScreen(ball: BallCountModel, state: BallUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BallTopAppBar()
        Column(
            modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 0.dp)
        ) {
            MyBall(ball)
            VerticalSpacer(height = 24.dp)
            BallBanner()
            VerticalSpacer(height = 48.dp)
            BallHistoryContent(state = state)
        }
    }
}

@Composable
fun BallTopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.Black)
    }
}

@Composable
fun MyBall(ball: BallCountModel) {
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
                text = ball.value + stringResource(id = R.string.ball_count),
                style = KnowllyTheme.typography.headline4
            )
        }
    }
}

@Composable
fun BallBanner() {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = KnowllyTheme.colors.grayF7,
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
fun BallHistoryContent(state: BallUiState) {
    when (state) {
        is BallUiState.Success -> {
            BallHistoryList(histories = state.histories)
        }
        BallUiState.Loading -> {}
        BallUiState.Empty -> {}
        BallUiState.Failure -> {}
    }
}

@Composable
fun BallHistoryList(
    histories: List<BallHistoryModel>
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
    history: BallHistoryModel
) {
    val sign = if (history.changedBallCount > 0) "+" else "-"

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
                text = "${history.date} | ${history.subtitle}",
                style = KnowllyTheme.typography.body1,
                color = KnowllyTheme.colors.gray8F
            )
        }
        Text(
            text = "$sign ${abs(history.changedBallCount)}" + stringResource(id = R.string.ball_count),
            style = KnowllyTheme.typography.subtitle1
        )
    }
}

@Preview
@Composable
private fun BallScreenPreview() {
    val tempBallHistoryList = listOf(
        BallHistoryModel(
            title = "클래스 운영",
            subtitle = "프랑스어",
            date = "05.09",
            changedBallCount = 1
        ),
        BallHistoryModel(
            title = "클래스 수강",
            subtitle = "요리 원데이 클래스",
            date = "05.09",
            changedBallCount = -1
        ),
        BallHistoryModel(
            title = "첫 가입 축하 볼",
            subtitle = "첫 가입 축하 볼",
            date = "05.08",
            changedBallCount = 1
        )
    )

    KnowllyTheme {
        BallScreen(
            ball = BallCountModel("1"),
            state = BallUiState.Success(tempBallHistoryList)
        )
    }
}
