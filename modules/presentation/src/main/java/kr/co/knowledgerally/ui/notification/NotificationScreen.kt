package kr.co.knowledgerally.ui.notification

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.component.HorizontalSpacer
import kr.co.knowledgerally.ui.component.VerticalSpacer
import kr.co.knowledgerally.ui.model.NotificationModel
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun NotificationScreen(viewModel: NotificationViewModel) {
    NotificationScreen(state = viewModel.state, onClickItem = { })
}

@Composable
fun NotificationScreen(
    state: NotificationUiState,
    onClickItem: () -> Unit
) {
    Scaffold(
        topBar = {
            // 임시 TopAppBar
            TopAppBar(
                backgroundColor = KnowllyTheme.colors.grayFF,
                elevation = 0.dp,
                contentPadding = PaddingValues(12.dp)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.Black)
                HorizontalSpacer(16.dp)
                Text("알림", style = KnowllyTheme.typography.subtitle1.copy(Color.Black))
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (state) {
                is NotificationUiState.Success -> NotificationList(
                    list = state.list,
                    onClickItem = onClickItem
                )
                NotificationUiState.Loading -> {
                    LoadingNotification()
                }
                NotificationUiState.Empty -> {
                    EmptyNotification()
                }
                NotificationUiState.Failure -> {
                    FailNotification()
                }
            }
        }
    }
}

@Composable
fun NotificationList(
    list: List<NotificationModel>,
    onClickItem: () -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        items(list) { notification ->
            NotificationListItem(notification, onClickItem)
            Divider(color = KnowllyTheme.colors.grayEF)
            VerticalSpacer(24.dp)
        }
    }
}

@Composable
fun NotificationListItem(
    notification: NotificationModel,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .height(84.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(notification.title, style = KnowllyTheme.typography.subtitle4)
            Icon(
                painter = painterResource(id = notification.iconRes),
                contentDescription = null,
                tint = Color.LightGray, // temporary
                modifier = Modifier
                    .padding(8.dp)
                    .size(48.dp, 48.dp)
            )
        }
        Spacer(Modifier.width(32.dp))
        Column {
            Text(notification.date, style = KnowllyTheme.typography.body1)
            VerticalSpacer(12.dp)
            Text(notification.text, style = KnowllyTheme.typography.subtitle3)
            VerticalSpacer(2.dp)
            Text(
                "${notification.lessonName} | ${notification.opponentName}",
                style = KnowllyTheme.typography.body2.copy(color = KnowllyTheme.colors.gray8F)
            )
        }
    }
}

@Composable
fun LoadingNotification() {
    CircularProgressIndicator()
}

@Composable
fun EmptyNotification() {
    Text("아직 알림이 없습니다")
}

@Composable
fun FailNotification() {
    Text("네트워크에 연결할 수 없습니다")
}

@Preview
@Composable
private fun NotificationScreenPreview() {
    val tempNotificationList = listOf(
        NotificationModel(
            id = 0,
            text = "여준님이 매칭을 수락했습니다.",
            lessonName = "윤영직의 안드로이드 교실",
            opponentName = "윤여준",
            date = "05.09",
            type = NotificationModel.Type.Player
        ),
        NotificationModel(
            id = 0,
            text = "새로운 매칭 신청을 확인해주세요.",
            lessonName = "클래스 제목",
            opponentName = "상대 유저 이름",
            date = "05.09",
            type = NotificationModel.Type.Coach
        )
    )

    KnowllyTheme {
        NotificationScreen(
            state = NotificationUiState.Success(tempNotificationList),
            onClickItem = {})
    }
}
