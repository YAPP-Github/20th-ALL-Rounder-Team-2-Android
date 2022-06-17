package kr.co.knowledgerally.ui.model

import kr.co.knowledgerally.domain.model.Notification
import kr.co.knowledgerally.ui.R
import java.time.LocalDate

data class NotificationModel(
    val id: Long,
    val text: String,
    val lessonTitle: String,
    val opponentName: String,
    val date: LocalDate,
    val type: Type
) {

    val titleRes = when (type) {
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
    date = date,
    type = when (type) {
        Notification.Type.Coach -> NotificationModel.Type.Coach
        Notification.Type.Player -> NotificationModel.Type.Player
    }
)
