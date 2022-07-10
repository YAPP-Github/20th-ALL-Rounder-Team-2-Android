package kr.co.knowledgerally.ui.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kr.co.knowledgerally.ui.component.KnowllyTabRow
import kr.co.knowledgerally.ui.component.Loading
import kr.co.knowledgerally.ui.component.VerticalSpacer

@Composable
fun PlayerScreen(viewModel: PlayerViewModel = hiltViewModel()) {
    val tabState by viewModel.tabState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    PlayerScreen(
        tabState = tabState,
        uiState = uiState,
        switchTab = viewModel::switchTab
    )
}

@Composable
fun PlayerScreen(
    tabState: PlayerTabState,
    uiState: PlayerUiState,
    switchTab: (Int) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        if (uiState.isInit) {
            Column(modifier = Modifier.fillMaxSize()) {
                KnowllyTabRow(
                    selectedTabIndex = tabState.selectedTab.ordinal,
                    tabs = tabState.titles.map { stringResource(id = it) },
                    onSelected = switchTab,
                    modifier = Modifier.padding(start = 24.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 0.dp)
                ) {
                    PlayerContentHeader(tab = tabState.selectedTab)
                    VerticalSpacer(height = 24.dp)
                    PlayerContent(tab = tabState.selectedTab, uiState = uiState)
                }
            }
        }
        if (uiState.isLoading) {
            Loading()
        }
    }
}
