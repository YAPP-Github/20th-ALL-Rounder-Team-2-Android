package kr.co.knowledgerally.ui.model

import kr.co.knowledgerally.domain.model.Notification
import kr.co.knowledgerally.ui.R
import java.time.LocalDateTime

data class NotificationModel(
    val content: String,
    val lectureTitle: String,
    val opponentName: String,
    val date: LocalDateTime,
    val type: Type
) {

    val titleRes = when (type) {
        Type.Coach -> R.string.notification_coach
        Type.Player -> R.string.notification_player
    }

    enum class Type { Coach, Player }
}

fun Notification.toPresentation(): NotificationModel = NotificationModel(
    content = content,
    lectureTitle = lectureTitle,
    opponentName = sender.profile.username,
    date = date,
    type = when (type) {
        Notification.Type.Coach -> NotificationModel.Type.Coach
        Notification.Type.Player -> NotificationModel.Type.Player
    }
)
