package kr.co.knowledgerally.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import kr.co.knowledgerally.ui.coach.navigation.coachGraph
import kr.co.knowledgerally.ui.home.navigation.HomeDestination
import kr.co.knowledgerally.ui.home.navigation.homeGraph
import kr.co.knowledgerally.ui.mypage.navigation.myPageGraph
import kr.co.knowledgerally.ui.player.navigation.playerGraph

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navigation: MainNavigation,
    startDestination: String = HomeDestination.route
) {
    NavHost(
        navController = navigation.navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeGraph()

        playerGraph()

        coachGraph(
            navigateToRegister = { navigation.navigateTo(MainDestination.Register) }
        )

        myPageGraph()
    }
}
