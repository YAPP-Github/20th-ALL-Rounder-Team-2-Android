package kr.co.knowledgerally.ui.model

import kr.co.knowledgerally.domain.model.BallHistory
import java.time.format.DateTimeFormatter

data class BallHistoryModel(
    val title: String,
    val content: String,
    val date: String,
    val count: Int
)

fun BallHistory.toPresentation(): BallHistoryModel = BallHistoryModel(
    title = title,
    content = content,
    date = date.format(DateTimeFormatter.ofPattern("MM.dd")),
    count = count
)
