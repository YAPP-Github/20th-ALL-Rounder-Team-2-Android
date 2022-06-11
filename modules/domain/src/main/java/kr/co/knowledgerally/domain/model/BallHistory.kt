package kr.co.knowledgerally.domain.model

import java.time.LocalDate

data class BallHistory(
    val title: String,
    val subtitle: String,
    val date: LocalDate,
    val changedBallCount: Int
)