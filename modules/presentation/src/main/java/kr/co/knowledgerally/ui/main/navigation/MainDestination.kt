package kr.co.knowledgerally.ui.main.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.coach.navigation.CoachDestination
import kr.co.knowledgerally.ui.home.navigation.HomeDestination
import kr.co.knowledgerally.ui.lesson.navigation.LessonDestination
import kr.co.knowledgerally.ui.mypage.navigation.MyPageDestination

enum class MainDestination(
    val route: String,
    val activeIconRes: Int,
    val inactiveIconRes: Int,
    @StringRes val iconTextResId: Int,
) {
    Home(
        route = HomeDestination.route,
        activeIconRes = R.drawable.ic_home_active,
        inactiveIconRes = R.drawable.ic_home_inactive,
        iconTextResId = R.string.home,
    ),
    Lesson(
        route = LessonDestination.route,
        activeIconRes = R.drawable.ic_player_active,
        inactiveIconRes = R.drawable.ic_player_inactive,
        iconTextResId = R.string.lesson,
    ),
    Coach(
        route = CoachDestination.route,
        activeIconRes = R.drawable.ic_coach_active,
        inactiveIconRes = R.drawable.ic_coach_inactive,
        iconTextResId = R.string.coach,
    ),
    MyPage(
        route = MyPageDestination.route,
        activeIconRes = R.drawable.ic_mypage_active,
        inactiveIconRes = R.drawable.ic_mypage_inactive,
        iconTextResId = R.string.mypage,
    )
}
