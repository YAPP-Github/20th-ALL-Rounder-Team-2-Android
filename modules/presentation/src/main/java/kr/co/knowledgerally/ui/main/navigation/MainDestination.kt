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

data class MainDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextResId: Int,
)

val TOP_LEVEL_DESTINATIONS = listOf(
    MainDestination(
        route = HomeDestination.route,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        iconTextResId = R.string.home,
    ),
    MainDestination(
        route = LessonDestination.route,
        selectedIcon = Icons.Filled.List,
        unselectedIcon = Icons.Outlined.List,
        iconTextResId = R.string.lesson,
    ),
    MainDestination(
        route = CoachDestination.route,
        selectedIcon = Icons.Filled.MailOutline,
        unselectedIcon = Icons.Outlined.MailOutline,
        iconTextResId = R.string.coach,
    ),
    MainDestination(
        route = MyPageDestination.route,
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        iconTextResId = R.string.mypage,
    ),
)
