package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
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
    val sender: UserResponse
) {

    enum class Type {
        @SerializedName("PLAYER")
        Player,
        @SerializedName("COACH")
        Coach
    }
}
