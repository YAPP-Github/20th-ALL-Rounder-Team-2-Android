package kr.co.knowledgerally.ui.register.schedule

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import kr.co.knowledgerally.ui.register.RegisterDestination
import kr.co.knowledgerally.ui.schedule.ScheduleActivity

fun NavGraphBuilder.registerScheduleGraph(
    navigateUp: () -> Unit
) {
    composable(RegisterDestination.Schedule.route) { navBackStackEntry ->
        val lectureId = navBackStackEntry.arguments?.getString("")
        RegisterScheduleRoute(
            navigateUp = navigateUp
        )
    }
}

@Composable
fun RegisterScheduleRoute(
    navigateUp: () -> Unit
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {

        }
    )
    RegisterScheduleScreen(
        navigateUp = navigateUp,
        navigateToSchedule = { launcher.launch(ScheduleActivity.getIntent(context)) },
    )
}
