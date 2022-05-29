package kr.co.knowledgerally.domain.model

// FIXME: 임시 모델로써 백엔드와 상의 필요
data class Notification(
    val id: Long,
    val text: String,
    val lessonName: String,
    val opponentName: String,
    val date: String,
    val type: String // "Coach" or "Player"
)