package kr.co.knowledgerally.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class MainNavigation(
    val navController: NavHostController,
    val navigateToRegister: () -> Unit,
) {

    fun navigateTo(destination: MainDestination) {
        when (destination) {
            MainDestination.Register -> {
                navigateToRegister()
                return
            }
            else -> Unit
        }

        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
fun rememberMainNavigation(
    navController: NavHostController = rememberNavController(),
    navigateToRegister: () -> Unit,
) = remember(navController) { MainNavigation(navController, navigateToRegister = navigateToRegister) }
