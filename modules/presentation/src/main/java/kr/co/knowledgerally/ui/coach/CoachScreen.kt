package kr.co.knowledgerally.ui.coach

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.applicant.ApplicantActivity
import kr.co.knowledgerally.ui.component.KnowllyTabRow
import kr.co.knowledgerally.ui.theme.KnowllyTheme

private const val INDEX_MATCHING = 0
private const val INDEX_SCHEDULED = 1
private const val INDEX_COMPLETED = 2

@Composable
fun CoachRoute(
    viewModel: CoachViewModel = hiltViewModel(),
    navigateToRegister: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val tabState by viewModel.tabState.collectAsState()
    val context = LocalContext.current

    val applicantLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            if (it.resultCode == Activity.RESULT_OK) {
                viewModel.refresh()
            }
        }
    )

    CoachScreen(
        uiState = uiState,
        tabState = tabState,
        navigateToRegister = navigateToRegister,
        switchTab = viewModel::switchTab,
        navigateToApplicant = { classId ->
            val intent = ApplicantActivity.getIntent(context, classId)
            applicantLauncher.launch(intent)
        }
    )
}

@Composable
fun CoachScreen(
    uiState: CoachUiState,
    tabState: CoachTabState,
    navigateToRegister: () -> Unit,
    navigateToApplicant: (classId: String) -> Unit,
    switchTab: (Int) -> Unit,
) {
    when (uiState) {
        CoachUiState.Loading -> Unit /* no-op */
        CoachUiState.Empty -> EmptyContent(navigateToRegister = navigateToRegister)
        is CoachUiState.Success -> CoachContent(
            uiState = uiState,
            tabState = tabState,
            switchTab = switchTab,
            navigateToApplicant = navigateToApplicant
        )
    }
}

@Composable
fun CoachContent(
    uiState: CoachUiState.Success,
    tabState: CoachTabState,
    navigateToApplicant: (classId: String) -> Unit,
    switchTab: (Int) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        KnowllyTabRow(
            selectedTabIndex = tabState.currentIndex,
            tabs = tabState.titles.map { stringResource(id = it) },
            onSelected = switchTab,
            modifier = Modifier.padding(start = 24.dp)
        )

        when (tabState.currentIndex) {
            INDEX_MATCHING -> MatchingTabContent(uiState.matchingClasses, navigateToApplicant)
            INDEX_SCHEDULED -> ScheduledTabContent()
            INDEX_COMPLETED -> CompletedTabContent()
        }
    }
}

@Preview(widthDp = 360, heightDp = 640, showBackground = true)
@Composable
private fun CoachContentPreview() {
    KnowllyTheme {
        CoachContent(
            uiState = CoachUiState.Success(emptyList(), emptyList(), emptyList()),
            tabState = CoachTabState(
                titles = listOf(
                    R.string.coach_matching,
                    R.string.coach_scheduled,
                    R.string.coach_completed
                ),
                currentIndex = 0,
            ),
            switchTab = { },
            navigateToApplicant = { },
        )
    }
}
