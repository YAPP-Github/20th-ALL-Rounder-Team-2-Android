package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.Notification
import java.time.LocalDateTime

data class NotificationEntity(
    val content: String,
    val lectureTitle: String,
    val receiver: UserEntity,
    val sender: UserEntity,
    val date: LocalDateTime,
    val type: Type
) {

    enum class Type { Coach, Player }
}

internal fun NotificationEntity.toDomain() =
    Notification(
        content = content,
        lectureTitle = lectureTitle,
        receiver = receiver.toDomain(),
        sender = sender.toDomain(),
        date = date,
        type = Notification.Type.valueOf(type.toString())
    )
