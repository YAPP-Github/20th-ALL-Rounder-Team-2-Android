package kr.co.knowledgerally.domain.model

import java.time.LocalDateTime

data class Applicant(
    val id: Long,
    val name: String,
    val content: String,
    val imageUrl: String?,
    val startAt: LocalDateTime,
)
