package kr.co.knowledgerally.ui.register.lounge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

fun NavGraphBuilder.registerLoungeGraph(
    navigateUp: () -> Unit,
    navigateToInfo: (Boolean) -> Unit,
    navigateToSchedule: (Long) -> Unit,
) {
    composable("register/lounge") {
        RegisterLoungeRoute(
            navigateToInfo = navigateToInfo,
            navigateToSchedule = navigateToSchedule,
            navigateUp = navigateUp
        )
    }
}

@Composable
fun RegisterLoungeRoute(
    viewModel: RegisterLoungeViewModel = hiltViewModel(),
    navigateUp: () -> Unit,
    navigateToInfo: (Boolean) -> Unit,
    navigateToSchedule: (Long) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    RegisterLoungeScreen(
        uiState = uiState,
        navigateUp = navigateUp,
        navigateToInfo = navigateToInfo,
        navigateToSchedule = navigateToSchedule,
    )
}
