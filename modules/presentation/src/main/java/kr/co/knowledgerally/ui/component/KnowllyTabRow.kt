package kr.co.knowledgerally.ui.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun KnowllyTabRow(
    selectedTabIndex: Int,
    tabs: List<String>,
    onSelected: (tabIndex: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val tabWidths = remember {
        val elements = Array(tabs.size) { 0.dp }
        mutableStateListOf(*elements)
    }
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier.height(TabHeight),
        containerColor = Color.Unspecified,
        contentColor = Color.Unspecified,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(
                    currentTabPosition = tabPositions[selectedTabIndex],
                    tabWidth = tabWidths[selectedTabIndex]
                ),
                color = KnowllyTheme.colors.gray00
            )
        },
        divider = { },
    ) {
        tabs.forEachIndexed { tabIndex, tab ->
            Tab(
                selected = selectedTabIndex == tabIndex,
                onClick = { onSelected(tabIndex) },
                modifier = modifier.fillMaxHeight(),
                selectedContentColor = KnowllyTheme.colors.gray00,
                unselectedContentColor = KnowllyTheme.colors.grayCC
            ) {
                Text(
                    text = tab,
                    style = KnowllyTheme.typography.subtitle2,
                    onTextLayout = { textLayoutResult ->
                        tabWidths[tabIndex] =
                            with(density) { textLayoutResult.size.width.toDp() }
                    }
                )
            }
        }
    }
}

/**
 * ref) [androidx.compose.material3.TabRowDefaults.tabIndicatorOffset]
 * ref) https://medium.com/@sukhdip_sandhu/jetpack-compose-scrollabletabrow-indicator-matches-width-of-text-e79c0e5826fe
 */
private fun Modifier.tabIndicatorOffset(
    currentTabPosition: TabPosition,
    tabWidth: Dp
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "customTabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = tabWidth,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    val indicatorOffset by animateDpAsState(
        targetValue = ((currentTabPosition.left + currentTabPosition.right - tabWidth) / 2),
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}

private val TabHeight = 48.dp

@Composable
@Preview(showBackground = true)
private fun KnowllyTabRowPreview() {
    KnowllyTheme {
        KnowllyTabRow(
            selectedTabIndex = 0,
            tabs = listOf("매칭 중", "예정된", "완료된"),
            onSelected = { },
        )
    }
}
