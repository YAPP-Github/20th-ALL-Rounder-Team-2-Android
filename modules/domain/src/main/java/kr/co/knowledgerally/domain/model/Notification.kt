package kr.co.knowledgerally.domain.model

import java.time.LocalDate

data class Notification(
    val id: Long,
    val text: String,
    val lessonTitle: String,
    val opponentName: String,
    val date: LocalDate,
    val type: Type
) {

    enum class Type { Coach, Player }
}
