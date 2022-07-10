package kr.co.knowledgerally.ui.register.schedule

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable

fun NavGraphBuilder.registerScheduleGraph(
    navigateUp: () -> Unit
) {
    composable(
        "register/schedule/{lectureId}",
        arguments = listOf(navArgument("lectureId") { type = NavType.LongType })
    ) {
        RegisterScheduleRoute(
            navigateUp = navigateUp
        )
    }
}

@Composable
fun RegisterScheduleRoute(navigateUp: () -> Unit) {
    RegisterScheduleScreen(onBackClick = navigateUp)
}
