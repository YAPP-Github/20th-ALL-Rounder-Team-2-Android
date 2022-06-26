package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.BallHistory
import java.time.LocalDate

data class BallHistoryEntity(
    val title: String,
    val content: String,
    val count: Int,
    val date: String
)

internal fun BallHistoryEntity.toDomain() =
    BallHistory(
        title = title,
        content = content,
        count = count,
        date = LocalDate.parse(date)
    )