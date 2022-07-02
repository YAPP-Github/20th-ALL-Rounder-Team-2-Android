package kr.co.knowledgerally.domain.model

import java.time.LocalDateTime

data class Lesson (
    val id: Int,
    val title: String,
    val thumbnailUrl: String?,
    val playerName: String?,
    val playerKakaoId: String?,
    val coachName: String,
    val coachKakaoId: String,
    val applicants: List<Applicant>?,
    val isReviewed: Boolean,
    val type: Type,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime
){

    enum class Type {
        Matching, Scheduled, Completed
    }
}
