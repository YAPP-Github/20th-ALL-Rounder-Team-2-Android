package kr.co.knowledgerally.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kr.co.knowledgerally.ui.coach.navigation.coachGraph
import kr.co.knowledgerally.ui.home.navigation.HomeDestination
import kr.co.knowledgerally.ui.home.navigation.homeGraph
import kr.co.knowledgerally.ui.lesson.navigation.lessonGraph
import kr.co.knowledgerally.ui.mypage.navigation.mypageGraph

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HomeDestination.route

) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeGraph()
        lessonGraph()
        coachGraph()
        mypageGraph()
    }
}
