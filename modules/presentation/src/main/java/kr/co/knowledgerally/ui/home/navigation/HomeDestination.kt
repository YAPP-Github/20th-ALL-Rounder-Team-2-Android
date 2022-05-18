package kr.co.knowledgerally.ui.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kr.co.knowledgerally.navigation.NavigationDestination
import kr.co.knowledgerally.ui.home.HomeScreen

object HomeDestination : NavigationDestination {
    override val route: String = "home"
}

fun NavGraphBuilder.homeGraph() {
    composable(HomeDestination.route) {
        HomeScreen()
    }
}
