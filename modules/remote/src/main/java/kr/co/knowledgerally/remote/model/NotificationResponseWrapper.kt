package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class NotificationResponseWrapper(
    @SerializedName("data")
    val notifications: List<NotificationResponse>
)
