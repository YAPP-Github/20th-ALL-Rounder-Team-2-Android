package kr.co.knowledgerally.ui.model

import kr.co.knowledgerally.domain.model.Notification
import kr.co.knowledgerally.ui.R

data class NotificationModel(
    val id: Long,
    val text: String,
    val lessonName: String,
    val opponentName: String,
    val date: String,
    val type: Type
) {

    val title = when (type) {
        NotificationModel.Type.Coach -> "운영 클래스"
        NotificationModel.Type.Player -> "수강 클래스"
    }

    val iconRes = when (type) {
        NotificationModel.Type.Coach -> R.drawable.ic_logo
        NotificationModel.Type.Player -> R.drawable.ic_logo
    }

    enum class Type { Coach, Player, /* ... */ }
}

fun Notification.toPresentation(): NotificationModel = NotificationModel(
    id = id,
    text = text,
    lessonName = lessonName,
    opponentName = opponentName,
    date = date,
    type = when (type) {
        "Coach" -> NotificationModel.Type.Coach
        else -> NotificationModel.Type.Player
    }
)
