package kr.co.knowledgerally.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun KnowllyTabRow(
    selectedTabIndex: Int,
    tabs: List<String>,
    onSelected: (tabIndex: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.height(TabHeight),
        horizontalArrangement = Arrangement.spacedBy(TabItemPadding)
    ) {
        tabs.forEachIndexed { tabIndex, tab ->
            val selected = selectedTabIndex == tabIndex
            KnowllyTab(onClick = { onSelected(tabIndex) }) {
                Text(text = tab, selected = selected)

                if (selected) {
                    Indicator()
                }
            }
        }
    }
}

@Composable
private fun KnowllyTab(
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .fillMaxHeight()
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ),
        content = content,
    )
}

@Composable
private fun BoxScope.Text(
    text: String,
    selected: Boolean,
) {
    Text(
        text = text,
        modifier = Modifier
            .wrapContentWidth()
            .align(Alignment.Center),
        style = KnowllyTheme.typography.subtitle2,
        color = if (selected) {
            KnowllyTheme.colors.gray44
        } else {
            KnowllyTheme.colors.grayCC
        },
    )
}

@Composable
private fun BoxScope.Indicator() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(TabIndicatorHeight)
            .background(KnowllyTheme.colors.gray00)
            .align(Alignment.BottomCenter)
    )
}

private val TabHeight = 48.dp
private val TabItemPadding = 20.dp
private val TabIndicatorHeight = 3.dp

@Composable
@Preview(showBackground = true, widthDp = 194)
private fun KnowllyTabRowPreview() {
    KnowllyTheme {
        KnowllyTabRow(
            selectedTabIndex = 0,
            tabs = listOf("매칭 중", "예정된", "완료된"),
            onSelected = { },
        )
    }
}
