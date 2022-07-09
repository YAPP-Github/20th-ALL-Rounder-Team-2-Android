package kr.co.knowledgerally.ui.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun SplashPage(
    visible: Boolean,
    items: List<PageItem>,
    startKnowlly: () -> Unit,
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    var lastPage by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
        content = {
            Scaffold(
                topBar = {
                    SplashTopAppBar(
                        canSkip = lastPage,
                        onSkip = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(page = items.lastIndex)
                            }
                        },
                    )
                },
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing),
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        verticalArrangement = Arrangement.Bottom,
                    ) {
                        HorizontalPager(
                            count = items.size,
                            state = pagerState,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        ) { page -> SplashPageItem(items[page]) }
                        StartButton(
                            visible = lastPage,
                            onClick = startKnowlly,
                        )
                        HorizontalPagerIndicator(
                            pagerState = pagerState,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(bottom = 30.dp),
                            activeColor = KnowllyTheme.colors.gray00,
                            inactiveColor = KnowllyTheme.colors.grayDD,
                        )
                    }
                },
            )
        }
    )
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .map { it == items.lastIndex }
            .collect { lastPage = it }
    }
}

@Composable
private fun StartButton(
    visible: Boolean,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(116.dp)
            .padding(bottom = 20.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            KnowllyContainedButton(
                text = stringResource(R.string.splash_start_button),
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )
        }
    }
}

@Preview(widthDp = 360, heightDp = 800)
@Composable
fun SplashPagePreview() {
    KnowllyTheme {
        SplashPage(
            visible = true,
            items = PageItems,
            startKnowlly = { },
        )
    }
}
