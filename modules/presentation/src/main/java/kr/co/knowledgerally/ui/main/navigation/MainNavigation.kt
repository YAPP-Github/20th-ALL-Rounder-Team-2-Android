package kr.co.knowledgerally.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class MainNavigation(private val navController: NavHostController) {

    fun navigateTo(destination: MainDestination) {
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
    navController: NavHostController = rememberNavController()
) = remember(navController) { MainNavigation(navController) }
