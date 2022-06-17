package kr.co.knowledgerally.ui.component

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
            .background(color = KnowllyTheme.colors.grayFF)
            .padding(top = 12.dp, end = 12.dp, bottom = 12.dp)
    ) {
        if (navigationType != NavigationType.None) {
            KnowllyTopAppBarNavigation(
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
fun KnowllyTopAppBarLogo(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_logo_home),
        contentDescription = null,
        modifier = modifier.height(24.dp)
    )
}

@Composable
fun KnowllyTopAppBarBall(
    ballCount: Int,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(36.dp),
        color = Color.Unspecified,
        border = BorderStroke(width = 2.dp, color = KnowllyTheme.colors.grayEF),
        modifier = Modifier
            .clip(RoundedCornerShape(36.dp))
            .clickable { onClick() }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_ball),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = ballCount.toString(), style = KnowllyTheme.typography.subtitle4
            )
        }
    }
}

@Composable
fun KnowllyTopAppBarNotification(
    onClick: () -> Unit
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_alarm),
        contentDescription = null,
        tint = KnowllyTheme.colors.gray00,
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .clickable { onClick() }
            .padding(4.dp)
    )
}

@Composable
fun KnowllyTopAppBarNavigation(
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
private fun KnowllyTopAppBarPreviewTitle() {
    KnowllyTheme {
        KnowllyTopAppBar(
            navigationType = NavigationType.None,
            onNavigationClick = {},
            actions = {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo_home),
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
            onNavigationClick = {},
        )
    }
}
