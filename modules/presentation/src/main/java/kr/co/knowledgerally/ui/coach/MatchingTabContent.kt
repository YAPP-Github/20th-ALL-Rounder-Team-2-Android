package kr.co.knowledgerally.ui.coach

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kr.co.knowledgerally.ui.R

@Composable
fun MatchingTabContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        CoachTitle(text = stringResource(R.string.coach_matching_title))
        CoachBanner(text = stringResource(id = R.string.coach_matching_banner))
    }
}
