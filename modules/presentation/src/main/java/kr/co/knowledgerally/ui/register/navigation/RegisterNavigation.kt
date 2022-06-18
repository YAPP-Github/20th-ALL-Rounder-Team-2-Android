package kr.co.knowledgerally.ui.register.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class RegisterNavigation(val navController: NavHostController) {

    fun navigateTo(destination: RegisterDestination) {
        navController.navigate(destination.route)
    }
}

@Composable
fun rememberRegisterNavigation(
    navController: NavHostController = rememberNavController()
): RegisterNavigation = remember(navController) { RegisterNavigation(navController) }
