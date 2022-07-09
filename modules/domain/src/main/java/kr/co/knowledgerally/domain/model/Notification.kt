package kr.co.knowledgerally.domain.model

import java.time.LocalDateTime

data class Notification(
    val content: String,
    val lectureTitle: String,
    val receiver: User,
    val sender: User,
    val date: LocalDateTime,
    val type: Type
) {

    enum class Type { Coach, Player }
}
