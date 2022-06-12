package kr.co.knowledgerally.ui.coach

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.component.KnowllyTabRow
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun CoachRunning(state: CoachUiState.Running) {
    val tabs = CoachTab.values()
    var selectedTab by remember { mutableStateOf(CoachTab.Matching) }

    Column(modifier = Modifier.fillMaxSize()) {
        KnowllyTabRow(
            selectedTabIndex = selectedTab.index,
            tabs = tabs.map { stringResource(id = it.tabTextResId) },
            onSelected = { selectedTab = tabs[it] }
        )
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            CoachTitle(tab = selectedTab)
            CoachBanner(tab = selectedTab)

            when (selectedTab) {
                CoachTab.Matching -> CoachMatching()
                CoachTab.Scheduled -> CoachScheduled()
                CoachTab.Completed -> CoachCompleted()
            }
        }
    }
}

@Composable
private fun CoachTitle(tab: CoachTab) {
    Text(
        text = stringResource(id = tab.titleTextResId),
        modifier = Modifier.padding(top = 24.dp, start = 24.dp),
        style = KnowllyTheme.typography.headline4,
    )
}

/**
 * TODO: 배너 스타일 변경
 */
@Composable
private fun CoachBanner(tab: CoachTab) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = Color(0xFFF5F5F5),
        modifier = Modifier.padding(start = 24.dp, top = 12.dp, end = 24.dp),
    ) {
        Text(
            text = stringResource(id = tab.bannerTextResId),
            style = KnowllyTheme.typography.body2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp)
        )
    }
}

@Composable
fun CoachMatching() {
}

@Composable
fun CoachScheduled() {
}

@Composable
fun CoachCompleted() {
}

@Preview(widthDp = 360, heightDp = 640, showBackground = true)
@Composable
private fun CoachRunningPreview() {
    KnowllyTheme {
        CoachRunning(state = CoachUiState.Running)
    }
}
