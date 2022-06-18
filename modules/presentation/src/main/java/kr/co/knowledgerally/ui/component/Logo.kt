package kr.co.knowledgerally.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R

@Composable
fun Logo(
    modifier: Modifier = Modifier,
    visible: Boolean = true,
) {
    val targetOffset: (Int) -> Int = { offset -> -offset }
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(initialOffsetX = targetOffset),
        exit = slideOutHorizontally(targetOffsetX = targetOffset),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_home),
            contentDescription = null,
            modifier = modifier.height(24.dp)
        )
    }
}
