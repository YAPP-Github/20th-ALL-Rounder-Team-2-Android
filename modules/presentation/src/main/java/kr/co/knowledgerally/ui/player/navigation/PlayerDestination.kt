package kr.co.knowledgerally.ui.player.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kr.co.knowledgerally.navigation.NavigationDestination
import kr.co.knowledgerally.ui.player.PlayerScreen

object PlayerDestination : NavigationDestination {
    override val route: String = "player"
}

fun NavGraphBuilder.playerGraph() {
    composable(PlayerDestination.route) {
        PlayerScreen()
    }
}
