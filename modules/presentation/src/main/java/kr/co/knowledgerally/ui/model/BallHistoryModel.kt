package kr.co.knowledgerally.ui.model

import kr.co.knowledgerally.domain.model.BallHistory
import java.time.format.DateTimeFormatter

data class BallHistoryModel(
    val title: String,
    val subtitle: String,
    val date: String,
    val type: Type,
    val count: Int,
) {

    enum class Type {
        Plus, Minus
    }
}

fun BallHistory.toPresentation(): BallHistoryModel = BallHistoryModel(
    title = title,
    subtitle = subtitle,
    date = date.format(DateTimeFormatter.ofPattern("MM.dd")),
    type = when (type) {
        BallHistory.Type.Plus -> BallHistoryModel.Type.Plus
        BallHistory.Type.Minus -> BallHistoryModel.Type.Minus
    },
    count = count
)
