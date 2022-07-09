package kr.co.knowledgerally.ui.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun SplashTopAppBar(
    canSkip: Boolean,
    onSkip: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(TopAppBarHeight),
    ) {
        AnimatedVisibility(
            visible = !canSkip,
            modifier = Modifier.align(Alignment.CenterEnd),
            enter = slideInHorizontally { it } + fadeIn(),
            exit = slideOutHorizontally { it } + fadeOut(),
        ) {
            Text(
                text = stringResource(id = R.string.splash_skip),
                modifier = Modifier
                    .padding(end = 20.dp)
                    .clickable { onSkip() }
                    .padding(4.dp),
                style = KnowllyTheme.typography.subtitle2,
                color = KnowllyTheme.colors.primaryDark,
            )
        }
    }
}

private val TopAppBarHeight = 56.dp
