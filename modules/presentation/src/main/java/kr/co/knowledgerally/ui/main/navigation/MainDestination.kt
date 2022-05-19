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
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextResId: Int,
) {
    Home(
        route = HomeDestination.route,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        iconTextResId = R.string.home,
    ),
    Lesson(
        route = LessonDestination.route,
        selectedIcon = Icons.Filled.List,
        unselectedIcon = Icons.Outlined.List,
        iconTextResId = R.string.lesson,
    ),
    Coach(
        route = CoachDestination.route,
        selectedIcon = Icons.Filled.MailOutline,
        unselectedIcon = Icons.Outlined.MailOutline,
        iconTextResId = R.string.coach,
    ),
    MyPage(
        route = MyPageDestination.route,
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        iconTextResId = R.string.mypage,
    )
}
