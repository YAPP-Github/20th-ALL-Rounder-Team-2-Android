package kr.co.knowledgerally.ui.coach.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kr.co.knowledgerally.navigation.NavigationDestination
import kr.co.knowledgerally.ui.coach.CoachScreen

object CoachDestination : NavigationDestination {
    override val route: String = "coach"
}

fun NavGraphBuilder.coachGraph() {
    composable(CoachDestination.route) {
        CoachScreen()
    }
}
