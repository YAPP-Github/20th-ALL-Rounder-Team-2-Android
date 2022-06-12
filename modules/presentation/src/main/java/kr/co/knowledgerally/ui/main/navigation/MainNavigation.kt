package kr.co.knowledgerally.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class MainNavigation(
    val navController: NavHostController,
    val onRegister: () -> Unit,
) {

    fun navigateTo(destination: MainDestination) {
        when (destination) {
            MainDestination.Register -> {
                onRegister()
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
    onRegister: () -> Unit,
) = remember(navController) { MainNavigation(navController, onRegister = onRegister) }
