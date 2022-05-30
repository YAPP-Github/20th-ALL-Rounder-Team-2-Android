package kr.co.knowledgerally.ui.model

import kr.co.knowledgerally.domain.model.Notification
import kr.co.knowledgerally.ui.R
import java.time.format.DateTimeFormatter

data class NotificationModel(
    val id: Long,
    val text: String,
    val lessonTitle: String,
    val opponentName: String,
    val date: String,
    val type: Type
) {

    val title = when (type) {
        Type.Coach -> R.string.notification_coach
        Type.Player -> R.string.notification_player
    }

    enum class Type { Coach, Player }
}

fun Notification.toPresentation(): NotificationModel = NotificationModel(
    id = id,
    text = text,
    lessonTitle = lessonTitle,
    opponentName = opponentName,
    date = date.format(DateTimeFormatter.ofPattern("MM.dd")),
    type = when (type) {
        Notification.Type.Coach -> NotificationModel.Type.Coach
        Notification.Type.Player -> NotificationModel.Type.Player
    }
)
