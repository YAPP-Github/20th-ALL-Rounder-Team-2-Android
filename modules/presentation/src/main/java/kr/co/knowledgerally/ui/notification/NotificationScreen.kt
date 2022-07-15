package kr.co.knowledgerally.ui.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import kr.co.knowledgerally.ui.component.KnowllyTopAppBar
import kr.co.knowledgerally.ui.component.Loading
import kr.co.knowledgerally.ui.component.VerticalSpacer
import kr.co.knowledgerally.ui.model.NotificationModel
import kr.co.knowledgerally.ui.theme.KnowllyTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun NotificationScreen(
    viewModel: NotificationViewModel,
    navigateUp: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    NotificationScreen(
        state = state,
        navigateUp = navigateUp,
        onNotificationClick = {}
    )
}

@Composable
fun NotificationScreen(
    state: NotificationUiState,
    navigateUp: () -> Unit,
    onNotificationClick: (NotificationModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        KnowllyTopAppBar(onNavigationClick = navigateUp)
        VerticalSpacer(height = 12.dp)
        Text(
            text = stringResource(R.string.notification_notification),
            style = KnowllyTheme.typography.headline3,
            modifier = Modifier.padding(start = 24.dp)
        )
        VerticalSpacer(height = 16.dp)
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
            Loading()
        }
        NotificationUiState.Empty -> {
            EmptyNotification(modifier = Modifier.fillMaxSize())
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
    LazyColumn {
        items(list) { notification ->
            NotificationListItem(notification, onNotificationClick)
            Divider(
                color = KnowllyTheme.colors.grayEF,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}

@Composable
fun NotificationListItem(
    notification: NotificationModel,
    onNotificationClick: (NotificationModel) -> Unit
) {
    val dateText =
        notification.date.format(DateTimeFormatter.ofPattern(stringResource(id = R.string.notification_date_format)))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNotificationClick(notification) }
            .padding(start = 24.dp, top = 20.dp, end = 24.dp, bottom = 12.dp)
    ) {
        NotificationIcon(notificationType = notification.type)
        HorizontalSpacer(width = 12.dp)
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                NotificationTitle(notificationType = notification.type)
                Text(text = dateText, style = KnowllyTheme.typography.body1)
            }
            NotificationContent(content = notification.content)
            Text(
                text = "${notification.lectureTitle} | ${notification.opponentName}",
                style = KnowllyTheme.typography.button2,
                color = KnowllyTheme.colors.gray8F
            )
        }
    }
}

@Composable
fun NotificationIcon(
    notificationType: NotificationModel.Type
) {
    val iconRes = when (notificationType) {
        NotificationModel.Type.Coach -> {
            R.drawable.ic_coach_active
        }
        NotificationModel.Type.Player -> {
            R.drawable.ic_player_active
        }
    }

    val iconTint = when (notificationType) {
        NotificationModel.Type.Coach -> {
            KnowllyTheme.colors.secondary
        }
        NotificationModel.Type.Player -> {
            KnowllyTheme.colors.primaryDark
        }
    }

    Icon(
        painter = painterResource(id = iconRes),
        contentDescription = null,
        tint = iconTint,
        modifier = Modifier.size(24.dp)
    )
}

@Composable
fun NotificationTitle(
    notificationType: NotificationModel.Type
) {
    val titleRes = when (notificationType) {
        NotificationModel.Type.Coach -> R.string.notification_coach
        NotificationModel.Type.Player -> R.string.notification_player
    }

    Text(text = stringResource(id = titleRes), style = KnowllyTheme.typography.subtitle2)
}

@Composable
fun NotificationContent(
    content: String
) {
    Text(
        text = content,
        style = KnowllyTheme.typography.body1,
        color = KnowllyTheme.colors.gray44
    )
}

@Composable
fun EmptyNotification(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_notification_empty),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 92.dp, vertical = 8.dp)
        )
        VerticalSpacer(height = 24.dp)
        Text(
            text = stringResource(R.string.notification_empty),
            style = KnowllyTheme.typography.body1,
            color = KnowllyTheme.colors.gray44
        )
    }
}

@Composable
fun FailNotification() {
    // TODO
    Text(text = "네트워크에 연결할 수 없습니다")
}

@Preview
@Composable
private fun NotificationScreenPreview() {
    val tempNotificationList = listOf(
        NotificationModel(
            content = "여준님이 매칭을 수락했습니다.",
            lectureTitle = "윤영직의 안드로이드 교실",
            opponentName = "윤여준",
            date = LocalDateTime.now(),
            type = NotificationModel.Type.Player
        ),
        NotificationModel(
            content = "새로운 매칭 신청을 확인해주세요.",
            lectureTitle = "클래스 제목",
            opponentName = "상대 유저 이름",
            date = LocalDateTime.now(),
            type = NotificationModel.Type.Coach
        )
    )

    KnowllyTheme {
        NotificationScreen(
            state = NotificationUiState.Success(tempNotificationList),
            navigateUp = {},
            onNotificationClick = {}
        )
    }
}

@Preview
@Composable
private fun NotificationScreenPreviewEmpty() {
    KnowllyTheme {
        NotificationScreen(
            state = NotificationUiState.Empty,
            navigateUp = {},
            onNotificationClick = {}
        )
    }
}

@Preview
@Composable
private fun NotificationScreenPreviewLoading() {
    KnowllyTheme {
        NotificationScreen(
            state = NotificationUiState.Loading,
            navigateUp = {},
            onNotificationClick = {}
        )
    }
}
