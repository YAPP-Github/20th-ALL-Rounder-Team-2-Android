package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.NotificationEntity
import java.time.LocalDateTime

data class NotificationResponse(
    @SerializedName("content")
    val content: String,
    @SerializedName("title")
    val lectureTitle: String,
    @SerializedName("sentDate")
    val date: LocalDateTime,
    @SerializedName("notiType")
    val type: Type,
    @SerializedName("receiver")
    val receiver: UserResponse,
    @SerializedName("sender")
    val sender: UserResponse,
    @SerializedName("read")
    val isRead: Boolean
) {

    enum class Type {
        @SerializedName("PLAYER")
        Player,

        @SerializedName("COACH")
        Coach
    }
}

internal fun NotificationResponse.toData() =
    NotificationEntity(
        content = content,
        lectureTitle = lectureTitle,
        date = date,
        type = type.toData(),
        sender = sender.toData(),
        receiver = receiver.toData()
    )

internal fun NotificationResponse.Type.toData() =
    when (this) {
        NotificationResponse.Type.Coach -> NotificationEntity.Type.Coach
        NotificationResponse.Type.Player -> NotificationEntity.Type.Player
    }
