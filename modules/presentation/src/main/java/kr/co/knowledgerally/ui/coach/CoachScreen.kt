package kr.co.knowledgerally.ui.coach

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Surface
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
import kr.co.knowledgerally.ui.component.Loading
import kr.co.knowledgerally.ui.lecture.LectureActivity
import kr.co.knowledgerally.ui.lecture.LectureType
import kr.co.knowledgerally.ui.theme.KnowllyTheme

private const val INDEX_MATCHING = 0
private const val INDEX_SCHEDULED = 1
private const val INDEX_COMPLETED = 2

@Composable
fun CoachScreen(
    viewModel: CoachViewModel = hiltViewModel(),
    navigateToRegister: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val tabState by viewModel.tabState.collectAsState()
    val context = LocalContext.current

    val activityLauncher = rememberLauncherForActivityResult(
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
        navigateToApplicant = { lectureInfoId ->
            val intent = ApplicantActivity.getIntent(context, lectureInfoId)
            activityLauncher.launch(intent)
        },
        navigateToLecture = { lectureInfoId: Long ->
            val intent =
                LectureActivity.getIntent(context, lectureInfoId, LectureType.Coach)
            activityLauncher.launch(intent)
        }
    )
}

@Composable
fun CoachScreen(
    uiState: CoachUiState,
    tabState: CoachTabState,
    navigateToRegister: () -> Unit,
    navigateToApplicant: (lectureInfoId: Long) -> Unit,
    navigateToLecture: (lectureInfoId: Long) -> Unit,
    switchTab: (Int) -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        when {
            !uiState.isInit -> Unit
            uiState.isEmpty -> EmptyItem(navigateToRegister = navigateToRegister)
            else -> CoachContent(
                uiState = uiState,
                tabState = tabState,
                switchTab = switchTab,
                navigateToApplicant = navigateToApplicant,
                navigateToLecture = navigateToLecture
            )
        }
        if (uiState.isLoading) {
            Loading()
        }
    }
}

@Composable
fun CoachContent(
    uiState: CoachUiState,
    tabState: CoachTabState,
    navigateToApplicant: (lectureInfoId: Long) -> Unit,
    navigateToLecture: (lectureInfoId: Long) -> Unit,
    switchTab: (Int) -> Unit,
) {
    val matchingScrollState = rememberScrollState()
    val scheduledScrollState = rememberScrollState()
    val completedScrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        KnowllyTabRow(
            selectedTabIndex = tabState.currentIndex,
            tabs = tabState.titles.map { stringResource(id = it) },
            onSelected = switchTab,
            modifier = Modifier.padding(start = 24.dp)
        )

        when (tabState.currentIndex) {
            INDEX_MATCHING -> MatchingTabContent(
                items = uiState.matchingLectures,
                navigateToApplicant = navigateToApplicant,
                navigateToLecture = navigateToLecture,
                scrollState = matchingScrollState
            )
            INDEX_SCHEDULED ->
                ScheduledTabContent(
                    items = uiState.scheduledLectures,
                    navigateToLecture = navigateToLecture,
                    scrollState = scheduledScrollState
                )
            INDEX_COMPLETED ->
                CompletedTabContent(
                    items = uiState.completedLectures,
                    navigateToLecture = navigateToLecture,
                    scrollState = completedScrollState
                )
        }
    }
}

@Preview(widthDp = 360, heightDp = 640, showBackground = true)
@Composable
private fun CoachContentPreview() {
    KnowllyTheme {
        CoachContent(
            uiState = CoachUiState(
                isInit = false,
                isLoading = false,
                lectureItems = emptyList()
            ),
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
            navigateToLecture = { }
        )
    }
}
