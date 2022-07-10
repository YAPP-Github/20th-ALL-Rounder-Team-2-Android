package kr.co.knowledgerally.ui.register.schedule

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable

fun NavGraphBuilder.registerScheduleGraph(
    navigateUp: () -> Unit,
    onResult: () -> Unit,
) {
    composable(
        "register/schedule/{lectureId}",
        arguments = listOf(navArgument("lectureId") { type = NavType.LongType })
    ) {
        RegisterScheduleScreen(
            onBackClick = navigateUp,
            onResult = onResult,
        )
    }
}
