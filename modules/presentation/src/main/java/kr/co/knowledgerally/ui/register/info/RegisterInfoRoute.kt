package kr.co.knowledgerally.ui.register.info

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable

fun NavGraphBuilder.registerInfoGraph(
    navigateUp: (Boolean) -> Unit,
    navigateToSchedule: (Long) -> Unit,
) {
    composable(
        route = "register/info/{isRoot}",
        arguments = listOf(navArgument("isRoot") { type = NavType.BoolType }),
    ) { navBackStackEntry ->
        val isRoot = navBackStackEntry.arguments
            ?.getBoolean("isRoot", false)
            ?: false

        BackHandler(
            enabled = true,
            onBack = { navigateUp(isRoot) }
        )
        RegisterInfoRoute(
            navigateUp = { navigateUp(isRoot) },
            navigateToSchedule = navigateToSchedule
        )
    }
}

@Composable
fun RegisterInfoRoute(
    navigateUp: () -> Unit,
    navigateToSchedule: (Long) -> Unit,
) {
    RegisterInfoScreen(
        navigateUp = navigateUp,
        navigateToSchedule = navigateToSchedule,
    )
}

