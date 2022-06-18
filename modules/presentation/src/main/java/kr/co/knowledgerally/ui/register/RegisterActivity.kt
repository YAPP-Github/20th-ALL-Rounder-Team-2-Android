package kr.co.knowledgerally.ui.register

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@AndroidEntryPoint
class RegisterActivity : BaseActivity() {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KnowllyTheme {
                val navController = rememberAnimatedNavController()
                AnimatedNavHost(
                    navController = navController,
                    startDestination = RegisterDestination.Register.route,
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                ) {
                    composable(RegisterDestination.Register.route) {
                        RegisterScreen(
                            viewModel = viewModel,
                            navigateUp = { finish() },
                            navigateToSchedule = { navController.navigate(RegisterDestination.Schedule.route) }
                        )
                    }
                    composable(RegisterDestination.Schedule.route) {
                        ScheduleScreen(
                            navigateUp = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
