package kr.co.knowledgerally.ui.register

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.ActivityTransition
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.register.navigation.RegisterDestination
import kr.co.knowledgerally.ui.register.navigation.rememberRegisterNavigation
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
                val navigation = rememberRegisterNavigation()

                NavHost(
                    navController = navigation.navController,
                    startDestination = RegisterDestination.Register.route,
                ) {
                    composable(RegisterDestination.Register.route) {
                        RegisterScreen(viewModel = viewModel)
                    }
                    composable(RegisterDestination.Schedule.route) {
                        ScheduleScreen()
                    }
                }
            }
        }
    }
}
