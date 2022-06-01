package kr.co.knowledgerally.ui.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import kr.co.knowledgerally.ui.model.NotificationModel
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun NotificationScreen(viewModel: NotificationViewModel) {
    val state by viewModel.state.collectAsState()

    NotificationScreen(
        state = state,
        onNotificationClick = {}
    )
}

@Composable
fun NotificationScreen(
    state: NotificationUiState,
    onNotificationClick: (NotificationModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 24.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.Black)
            HorizontalSpacer(16.dp)
            Text(text = "알림", style = KnowllyTheme.typography.subtitle1.copy(Color.Black))
        }
        NotificationContent(state = state, onNotificationClick = onNotificationClick)
    }
}

@Composable
fun NotificationContent(
    state: NotificationUiState,
    onNotificationClick: (NotificationModel) -> Unit
) {
    when (state) {
        is NotificationUiState.Success -> NotificationList(
            list = state.list,
            onNotificationClick = onNotificationClick
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

@Composable
fun NotificationList(
    list: List<NotificationModel>,
    onNotificationClick: (NotificationModel) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(start = 24.dp, top = 12.dp, end = 24.dp, bottom = 0.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(list) { notification ->
            NotificationListItem(notification, onNotificationClick)
            Divider(color = KnowllyTheme.colors.grayEF)
        }
    }
}

@Composable
fun NotificationListItem(
    notification: NotificationModel,
    onNotificationClick: (NotificationModel) -> Unit
) {
    Row(
        modifier = Modifier
            .height(84.dp)
            .fillMaxWidth()
    ) {
        Column {
            NotificationTitle(text = stringResource(id = notification.titleRes))
            NotificationIcon(type = notification.type)
        }
        HorizontalSpacer(32.dp)
        Column {
            NotificationDate(text = notification.date)
            VerticalSpacer(12.dp)
            NotificationText(text = notification.text)
            VerticalSpacer(2.dp)
            NotificationSubtext(
                lessonTitle = notification.lessonTitle,
                opponentName = notification.opponentName
            )
        }
    }
}

@Composable
fun NotificationTitle(text: String) {
    Text(
        text = text,
        style = KnowllyTheme.typography.subtitle4,
        modifier = Modifier.padding(horizontal = 8.dp)
    )
}

@Composable
fun NotificationIcon(type: NotificationModel.Type) {
    Icon(
        painter = painterResource(
            id = when (type) {
                NotificationModel.Type.Coach -> R.drawable.ic_logo
                NotificationModel.Type.Player -> R.drawable.ic_logo
            }
        ),
        contentDescription = null,
        tint = Color.LightGray, // temporary
        modifier = Modifier
            .padding(8.dp)
            .size(48.dp)
    )
}

@Composable
fun NotificationDate(text: String) {
    Text(text = text, style = KnowllyTheme.typography.body1)
}

@Composable
fun NotificationText(text: String) {
    Text(text = text, style = KnowllyTheme.typography.subtitle3)
}

@Composable
fun NotificationSubtext(lessonTitle: String, opponentName: String) {
    Text(
        text = "$lessonTitle | $opponentName",
        style = KnowllyTheme.typography.body2.copy(color = KnowllyTheme.colors.gray8F)
    )
}

@Composable
fun LoadingNotification() {
    CircularProgressIndicator()
}

@Composable
fun EmptyNotification() {
    Text(text = "아직 알림이 없습니다")
}

@Composable
fun FailNotification() {
    Text(text = "네트워크에 연결할 수 없습니다")
}

@Preview
@Composable
private fun NotificationScreenPreview() {
    val tempNotificationList = listOf(
        NotificationModel(
            id = 0,
            text = "여준님이 매칭을 수락했습니다.",
            lessonTitle = "윤영직의 안드로이드 교실",
            opponentName = "윤여준",
            date = "05.09",
            type = NotificationModel.Type.Player
        ),
        NotificationModel(
            id = 0,
            text = "새로운 매칭 신청을 확인해주세요.",
            lessonTitle = "클래스 제목",
            opponentName = "상대 유저 이름",
            date = "05.09",
            type = NotificationModel.Type.Coach
        )
    )

    KnowllyTheme {
        NotificationScreen(
            state = NotificationUiState.Success(tempNotificationList),
            onNotificationClick = {}
        )
    }
}