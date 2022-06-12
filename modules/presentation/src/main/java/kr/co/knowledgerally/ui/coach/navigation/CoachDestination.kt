package kr.co.knowledgerally.ui.coach.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kr.co.knowledgerally.navigation.NavigationDestination
import kr.co.knowledgerally.ui.coach.CoachRoute

object CoachDestination : NavigationDestination {
    override val route: String = "coach"
}

fun NavGraphBuilder.coachGraph(navigateToRegister: () -> Unit) {
    composable(CoachDestination.route) {
        CoachRoute(navigateToRegister = navigateToRegister)
    }
}
