package kr.co.knowledgerally.ui.player

import kr.co.knowledgerally.ui.R

data class PlayerTabState(
    val titles: List<Int>,
    val selectedTab: Tab
) {

    enum class Tab {
        Matching, Scheduled, Completed
    }

    companion object {
        val DEFAULT = PlayerTabState(
            titles = listOf(
                R.string.player_matching,
                R.string.player_scheduled,
                R.string.player_completed
            ),
            selectedTab = Tab.Matching
        )
    }
}