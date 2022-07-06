package kr.co.knowledgerally.ui.register.lounge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.component.KnowllyTopAppBar
import kr.co.knowledgerally.ui.component.Loading
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun RegisterLoungeScreen(
    uiState: RegisterLoungeUiState,
    navigateUp: () -> Unit,
    navigateToInfo: (Boolean) -> Unit,
    navigateToSchedule: (String) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when (uiState) {
            RegisterLoungeUiState.Loading -> Loading()
            RegisterLoungeUiState.NoLecture -> navigateToInfo(true)
            is RegisterLoungeUiState.Lectures -> RegisterLoungeContent(
                lectures = emptyList(),
                navigateToSchedule = navigateToSchedule,
                navigateToInfo = { navigateToInfo(false) },
                navigateUp = navigateUp,
            )
        }
    }
}

@Composable
fun RegisterLoungeContent(
    lectures: List<String>,
    navigateUp: () -> Unit,
    navigateToSchedule: (String) -> Unit,
    navigateToInfo: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KnowllyTheme.colors.grayF7)
    ) {
        KnowllyTopAppBar(onNavigationClick = navigateUp)

        LoungeHeader(onNewLecture = navigateToInfo)

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = KnowllyTheme.colors.grayFF)
                .weight(1f),
            contentPadding = PaddingValues(24.dp)
        ) {
            item { RegisterLoungeItemHeader() }

            items(
                items = lectures,
                key = { it }
            ) {
                RegisterLoungeItem(navigateToSchedule = navigateToSchedule)
                RegisterLoungeItemDivider()
            }
        }
    }
}

@Composable
fun LoungeHeader(
    onNewLecture: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 40.dp)
    ) {
        Text(
            text = stringResource(id = R.string.register_lounge_title),
            modifier = Modifier.padding(horizontal = 24.dp),
            style = KnowllyTheme.typography.headline4
        )
        Text(
            text = stringResource(id = R.string.register_lounge_description),
            modifier = Modifier.padding(start = 24.dp, top = 4.dp, end = 24.dp),
            style = KnowllyTheme.typography.body1,
            color = KnowllyTheme.colors.gray8F,
        )

        KnowllyContainedButton(
            text = stringResource(id = R.string.register_lounge_title),
            onClick = onNewLecture,
            modifier = Modifier
                .padding(start = 16.dp, top = 24.dp, end = 16.dp)
                .fillMaxWidth()
                .height(56.dp)
        )
    }
}
