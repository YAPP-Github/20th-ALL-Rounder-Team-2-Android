package kr.co.knowledgerally.ui.schedule;

enum class TimeRange(
    private val first: IntRange,
    private val second: IntRange,
) {
    Hours(
        first = (1..2),
        second = (1..12),
    ),
    Minutes(
        first = (0..5),
        second = (0..59)
    );

    operator fun contains(value: String): Boolean {
        val length = value.length
        return when {
            length == 2 && value.toInt() in second -> true
            length == 1 && value.toInt() in first -> true
            length == 0 -> true
            else -> false
        }
    }
}
