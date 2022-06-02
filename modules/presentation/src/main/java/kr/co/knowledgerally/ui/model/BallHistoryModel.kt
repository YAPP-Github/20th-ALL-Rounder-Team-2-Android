package kr.co.knowledgerally.ui.model

import kr.co.knowledgerally.domain.model.BallHistory
import java.time.format.DateTimeFormatter

data class BallHistoryModel(
    val title: String,
    val subtitle: String,
    val date: String,
    val delta: Int
)

fun BallHistory.toPresentation(): BallHistoryModel = BallHistoryModel(
    title = title,
    subtitle = subtitle,
    date = date.format(DateTimeFormatter.ofPattern("MM.dd")),
    delta = delta
)
