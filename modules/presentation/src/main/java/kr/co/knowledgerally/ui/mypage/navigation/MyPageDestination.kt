package kr.co.knowledgerally.ui.mypage.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kr.co.knowledgerally.navigation.NavigationDestination
import kr.co.knowledgerally.ui.mypage.MyPageScreen

object MyPageDestination : NavigationDestination {
    override val route: String = "mypage"
}

fun NavGraphBuilder.myPageGraph() {
    composable(MyPageDestination.route) {
        MyPageScreen()
    }
}
