package kr.co.knowledgerally.ui.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.ActivityTransition
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.schedule.ScheduleActivity
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@AndroidEntryPoint
class RegisterActivity : BaseActivity() {

    private val viewModel: RegisterViewModel by viewModels()

    override val activityTransition: ActivityTransition
        get() = ActivityTransition.Cover

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KnowllyTheme {
                val navController = rememberAnimatedNavController()
                AnimatedNavHost(
                    navController = navController,
                    startDestination = RegisterDestination.Register.route,
                    enterTransition = {
                        slideInHorizontally { it }
                    },
                    exitTransition = {
                        slideOutHorizontally { -it }
                    },
                    popEnterTransition = {
                        slideInHorizontally { -it }
                    },
                    popExitTransition = {
                        slideOutHorizontally { it }
                    }
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
                            navigateUp = { navController.popBackStack() },
                            navigateToSchedule = ::navigateToSchedule,
                        )
                    }
                }
            }
        }
    }

    private fun navigateToSchedule() {
        // TODO: StartActivityForResult
        val intent = ScheduleActivity.getIntent(this)
        startActivity(intent)
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }
}
