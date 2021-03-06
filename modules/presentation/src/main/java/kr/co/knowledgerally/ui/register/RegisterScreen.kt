package kr.co.knowledgerally.ui.register

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kr.co.knowledgerally.ui.register.info.registerInfoGraph
import kr.co.knowledgerally.ui.register.lounge.registerLoungeGraph
import kr.co.knowledgerally.ui.register.schedule.registerScheduleGraph

@Composable
fun RegisterScreen(
    navigateUp: () -> Unit,
    onResult: () -> Unit,
) {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = "register/lounge",
        enterTransition = { slideInHorizontally { it } },
        exitTransition = { slideOutHorizontally { -it } },
        popEnterTransition = { slideInHorizontally { -it } },
        popExitTransition = { slideOutHorizontally { it } }
    ) {
        registerLoungeGraph(
            navigateUp = navigateUp,
            navigateToInfo = {
                navController.navigate("register/info/$it")
            },
            navigateToSchedule = { lectureId ->
                navController.navigate("register/schedule/$lectureId")
            }
        )
        registerInfoGraph(
            navigateUp = { isRoot ->
                if (isRoot) {
                    navigateUp()
                } else {
                    navController.popBackStack()
                }
            },
            navigateToSchedule = { lectureId ->
                navController.navigate("register/schedule/$lectureId")
            }
        )
        registerScheduleGraph(
            navigateUp = { navController.popBackStack() },
            onResult = onResult,
        )
    }
}
