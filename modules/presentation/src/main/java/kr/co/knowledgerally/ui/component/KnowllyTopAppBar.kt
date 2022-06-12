package kr.co.knowledgerally.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.ball.BallActivity
import kr.co.knowledgerally.ui.theme.KnowllyTheme

private val TOP_APP_BAR_HEIGHT = 56.dp

@Composable
fun KnowllyTopAppBar(
    showTitle: Boolean,
    showPrevious: Boolean,
    onPreviousClick: () -> Unit,
    ballCount: Int? = null,
    actions: @Composable @ExtensionFunctionType RowScope.() -> Unit = {}
) {
    Surface(
        color = KnowllyTheme.colors.grayFF
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(TOP_APP_BAR_HEIGHT)
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    if (showPrevious) {
                        IconButton(
                            onClick = onPreviousClick,
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_previous),
                                contentDescription = null
                            )
                        }
                    }
                    if (showTitle) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_logo_topappbar),
                            contentDescription = null,
                            modifier = Modifier.height(24.dp)
                        )
                    }
                }
                if (ballCount != null) {
                    KnowllyTopAppBarBall(ballCount = ballCount)
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.End),
                    verticalAlignment = Alignment.CenterVertically,
                    content = actions
                )
            }
        }
    }
}

@Composable
fun KnowllyTopAppBarBall(
    ballCount: Int
) {
    val context = LocalContext.current

    Surface(
        shape = RoundedCornerShape(36.dp),
        color = Color.Unspecified,
        border = BorderStroke(width = 2.dp, color = KnowllyTheme.colors.grayEF),
        modifier = Modifier
            .clip(RoundedCornerShape(36.dp))
            .clickable { BallActivity.startActivity(context) }
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
                modifier = Modifier.requiredSize(20.dp)
            )
            Text(
                text = ballCount.toString(), style = KnowllyTheme.typography.subtitle4
            )
        }
    }
}

@Preview
@Composable
private fun KnowllyTopAppBarPreviewHome() {
    KnowllyTheme {
        KnowllyTopAppBar(
            showTitle = true,
            showPrevious = false,
            ballCount = 10,
            onPreviousClick = { },
            actions = {
                IconButton(onClick = { }, modifier = Modifier.size(28.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_alarm),
                        contentDescription = null
                    )
                }
            })
    }
}

@Preview
@Composable
private fun KnowllyTopAppBarPreviewNext() {
    KnowllyTheme {
        KnowllyTopAppBar(
            showTitle = false,
            showPrevious = true,
            onPreviousClick = { },
            actions = {
                IconButton(
                    onClick = { },
                    modifier = Modifier.size(28.dp)
                ) {
                    Text(text = "다음", color = KnowllyTheme.colors.primaryDark)
                }
            })
    }
}
