package kr.co.knowledgerally.ui.register.info

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun CategorySelectContent(
    sheetState: ModalBottomSheetState,
    onSelect: (CategoryItem) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    Column {
        Text(
            text = stringResource(R.string.category_select),
            style = KnowllyTheme.typography.subtitle1,
            modifier = Modifier.padding(top = 24.dp, start = 24.dp)
        )

        CategorySelectItems(
            categories = CategoryItem.values().toList(),
            onClick = {
                onSelect(it)
                coroutineScope.launch { sheetState.hide() }
            },
        )
    }
}

@Composable
private fun CategorySelectItems(
    categories: List<CategoryItem>,
    onClick: (CategoryItem) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(
            start = 32.dp,
            top = 8.dp,
            bottom = 24.dp,
            end = 32.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        content = {
            items(categories) {
                CategorySelectItem(category = it, onClick = onClick)
            }
        })
}

@Composable
private fun CategorySelectItem(
    category: CategoryItem,
    onClick: (CategoryItem) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick(category) }
            .padding(vertical = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = category.drawableResId),
            contentDescription = null,
            modifier = Modifier.size(48.dp),
            tint = Color.Unspecified,
        )
        Text(
            text = stringResource(id = category.textResId),
            style = KnowllyTheme.typography.subtitle4,
            color = KnowllyTheme.colors.gray6B,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategorySelectContentPreview() {
    KnowllyTheme {
        CategorySelectContent(
            sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded),
            onSelect = { }
        )
    }
}
