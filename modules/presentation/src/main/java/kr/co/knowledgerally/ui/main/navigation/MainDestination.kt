package kr.co.knowledgerally.ui.main.navigation

import androidx.annotation.StringRes
import kr.co.knowledgerally.ui.R

private const val NO_ROUTE = "no_route"
private const val ROUTE_HOME = "home"
private const val ROUTE_PLAYER = "player"
private const val ROUTE_COACH = "coach"
private const val ROUTE_MY_PAGE = "mypage"

enum class MainDestination(
    val route: String,
    val activeIconResId: Int,
    val inactiveIconResId: Int,
    @StringRes val iconTextResId: Int,
) {
    Home(
        route = ROUTE_HOME,
        activeIconResId = R.drawable.ic_home_active,
        inactiveIconResId = R.drawable.ic_home_inactive,
        iconTextResId = R.string.nav_home,
    ),
    Player(
        route = ROUTE_PLAYER,
        activeIconResId = R.drawable.ic_player_active,
        inactiveIconResId = R.drawable.ic_player_inactive,
        iconTextResId = R.string.nav_player,
    ),
    Register(
        route = NO_ROUTE,
        activeIconResId = R.drawable.ic_addclass_inactive,
        inactiveIconResId = R.drawable.ic_addclass_inactive,
        iconTextResId = R.string.nav_register
    ),
    Coach(
        route = ROUTE_COACH,
        activeIconResId = R.drawable.ic_coach_active,
        inactiveIconResId = R.drawable.ic_coach_inactive,
        iconTextResId = R.string.nav_coach,
    ),
    MyPage(
        route = ROUTE_MY_PAGE,
        activeIconResId = R.drawable.ic_mypage_active,
        inactiveIconResId = R.drawable.ic_mypage_inactive,
        iconTextResId = R.string.nav_mypage,
    );
}
