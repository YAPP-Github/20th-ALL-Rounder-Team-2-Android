package kr.co.knowledgerally.domain.model

import java.time.LocalDate

data class Applicant(
    val id: String,
    val name: String,
    val content: String,
    val imageUrl: String,
    val startAt: LocalDate,
)
