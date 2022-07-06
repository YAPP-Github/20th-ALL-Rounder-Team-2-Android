package kr.co.knowledgerally.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.theme.KnowllyTheme

private val TOP_APP_BAR_HEIGHT = 56.dp

@Composable
fun KnowllyTopAppBar(
    navigationType: NavigationType = NavigationType.Back,
    onNavigationClick: (() -> Unit) = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT)
            .padding(top = 12.dp, end = 12.dp, bottom = 12.dp)
    ) {
        if (navigationType != NavigationType.None) {
            NavigationIcon(
                navigationType = navigationType,
                onNavigationClick = onNavigationClick
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
private fun NavigationIcon(
    navigationType: NavigationType,
    onNavigationClick: () -> Unit
) {
    IconButton(onClick = onNavigationClick, modifier = Modifier.padding(start = 12.dp)) {
        Icon(
            painter = painterResource(
                id = if (navigationType == NavigationType.Back) {
                    R.drawable.ic_previous
                } else {
                    R.drawable.ic_clear
                }
            ),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

enum class NavigationType {
    Back, Close, None
}

@Preview
@Composable
private fun KnowllyTopAppBarPreview() {
    KnowllyTheme {
        KnowllyTopAppBar(
            navigationType = NavigationType.Close,
            onNavigationClick = {},
        )
    }
}
