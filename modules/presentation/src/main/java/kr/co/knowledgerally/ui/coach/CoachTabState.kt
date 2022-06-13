package kr.co.knowledgerally.ui.coach

import kr.co.knowledgerally.ui.R

data class CoachTabState(
    val titles: List<Int>,
    val currentIndex: Int,
) {

    companion object {
        val Default = CoachTabState(
            titles = listOf(
                R.string.coach_matching,
                R.string.coach_scheduled,
                R.string.coach_completed
            ),
            currentIndex = 0,
        )
    }
}
