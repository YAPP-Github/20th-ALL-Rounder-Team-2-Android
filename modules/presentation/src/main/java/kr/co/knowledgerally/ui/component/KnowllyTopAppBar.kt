package kr.co.knowledgerally.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.theme.KnowllyTheme

private val TOP_APP_BAR_HEIGHT = 56.dp

@Composable
fun KnowllyTopAppBar(
    navigationType: NavigationType = NavigationType.Back,
    onNagationClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT)
            .background(color = KnowllyTheme.colors.grayFF)
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        if (navigationType != NavigationType.None) {
            KnowllyTopAppBarNavigation(
                navigationType = navigationType,
                onNavigationClick = onNagationClick
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.End),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f),
            content = actions
        )
    }
}

@Composable
fun KnowllyTopAppBarNavigation(
    navigationType: NavigationType,
    onNavigationClick: () -> Unit
) {
    val navigationIconRes =
        if (navigationType == NavigationType.Back) {
            R.drawable.ic_previous
        } else {
            R.drawable.ic_clear
        }

    Surface(
        modifier = Modifier
            .clip(shape = CircleShape)
            .size(24.dp)
            .clickable { onNavigationClick() }
    ) {
        Icon(
            painter = painterResource(id = navigationIconRes),
            contentDescription = null
        )
    }
}

enum class NavigationType {
    Back, Close, None
}

@Preview
@Composable
private fun KnowllyTopAppBarPreviewTitle() {
    KnowllyTheme {
        KnowllyTopAppBar(
            navigationType = NavigationType.None,
            onNagationClick = {},
            actions = {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo_topappbar),
                    contentDescription = null,
                    modifier = Modifier.height(24.dp)
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {}, modifier = Modifier.size(28.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_alarm),
                        contentDescription = null
                    )
                }
            }
        )
    }
}

@Preview
@Composable
private fun KnowllyTopAppBarPreviewNoTitle() {
    KnowllyTheme {
        KnowllyTopAppBar(
            navigationType = NavigationType.Close,
            onNagationClick = {},
        )
    }
}
