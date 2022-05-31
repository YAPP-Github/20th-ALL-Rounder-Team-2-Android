package kr.co.knowledgerally.domain.model

import java.time.LocalDate

data class BallHistory(
    val title: String,
    val subtitle: String,
    val date: LocalDate,
    val type: Type,
    val count: Int
) {

    enum class Type {
        Plus, Minus
    }
}