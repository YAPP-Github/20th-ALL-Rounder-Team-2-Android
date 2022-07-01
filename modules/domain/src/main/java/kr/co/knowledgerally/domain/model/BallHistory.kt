package kr.co.knowledgerally.domain.model

import java.time.LocalDate

data class BallHistory(
    val title: String,
    val content: String,
    val date: LocalDate,
    val count: Int
)