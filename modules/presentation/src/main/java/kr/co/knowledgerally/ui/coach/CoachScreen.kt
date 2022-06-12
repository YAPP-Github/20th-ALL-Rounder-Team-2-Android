package kr.co.knowledgerally.ui.coach

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CoachScreen(viewModel: CoachViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        CoachUiState.Loading -> Unit /* no-op */
        CoachUiState.Empty -> CoachEmpty()
        CoachUiState.Running -> TODO()
    }
}
