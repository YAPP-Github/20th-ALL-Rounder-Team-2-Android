package kr.co.knowledgerally.ui.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.component.KnowllyDropdown
import kr.co.knowledgerally.ui.component.KnowllyMultilineTextField
import kr.co.knowledgerally.ui.component.KnowllySinglelineTextField
import kr.co.knowledgerally.ui.component.KnowllyTopAppBar
import kr.co.knowledgerally.ui.component.NavigationType
import kr.co.knowledgerally.ui.component.PageIndicator
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun RegisterScreen(viewModel: RegisterViewModel) {
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetContent = {
            CategorySelectSheetContent(
                sheetState = sheetState,
                onSelect = { /* TODO */ },
            )
        },
        sheetState = sheetState,
        sheetElevation = Dp.Unspecified,
        sheetBackgroundColor = KnowllyTheme.colors.grayFF,
        sheetShape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp)
    ) {
        RegisterContent(
            onCategorySelect = {
                coroutineScope.launch {
                    sheetState.show()
                }
            }
        )
    }
}

@Composable
fun RegisterContent(
    onCategorySelect: () -> Unit,
) {
    Column {
        KnowllyTopAppBar(NavigationType.Close)
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            val modifier = Modifier.padding(horizontal = 24.dp)

            Row(modifier = modifier.padding(top = 12.dp)) {
                PageIndicator(value = 1, isActive = true)
                PageIndicator(
                    value = 2,
                    isActive = false,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Text(
                text = stringResource(id = R.string.register_title),
                modifier = modifier.padding(top = 24.dp),
                style = KnowllyTheme.typography.headline3,
            )

            Text(
                text = stringResource(id = R.string.register_category),
                modifier = modifier.padding(top = 40.dp),
                style = KnowllyTheme.typography.subtitle2,
                color = KnowllyTheme.colors.gray44,
            )
            KnowllyDropdown(
                value = stringResource(id = R.string.register_category_placeholder),
                onClick = onCategorySelect,
                modifier = modifier.padding(top = 8.dp),
                isSelected = false,
            )

            Text(
                text = stringResource(id = R.string.register_name),
                modifier = modifier.padding(top = 24.dp),
                style = KnowllyTheme.typography.subtitle2,
                color = KnowllyTheme.colors.gray44,
            )
            KnowllySinglelineTextField(
                value = "",
                onValueChange = { },
                modifier = modifier.padding(top = 8.dp),
                counterEnabled = true,
                counterMaxLength = 20,
                placeholder = stringResource(id = R.string.register_name_placeholder),
            )

            Text(
                text = stringResource(id = R.string.register_introduction),
                modifier = modifier.padding(top = 24.dp),
                style = KnowllyTheme.typography.subtitle2,
                color = KnowllyTheme.colors.gray44,
            )
            KnowllyMultilineTextField(
                value = "",
                onValueChange = { },
                modifier = modifier.padding(top = 8.dp),
                counterEnabled = true,
                counterMaxLength = 500,
                placeholder = stringResource(id = R.string.register_introduction_placeholder),
                minHeight = 184.dp,
            )
            Row(
                modifier = modifier.padding(top = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(id = R.string.register_tag),
                    style = KnowllyTheme.typography.subtitle2,
                    color = KnowllyTheme.colors.gray44,
                )
                Text(
                    text = stringResource(id = R.string.register_tag_max_count),
                    style = KnowllyTheme.typography.body2,
                    color = KnowllyTheme.colors.gray44,
                )
            }
            KnowllySinglelineTextField(
                value = "",
                onValueChange = { },
                modifier = modifier.padding(top = 8.dp),
                helperTextEnabled = true,
                helperText = stringResource(id = R.string.register_tag_helper_text),
                placeholder = stringResource(id = R.string.register_tag_placeholder),
            )

            Row(
                modifier = modifier.padding(top = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(id = R.string.register_image),
                    style = KnowllyTheme.typography.subtitle2,
                    color = KnowllyTheme.colors.gray44,
                )
                Text(
                    text = stringResource(id = R.string.register_image_optional),
                    style = KnowllyTheme.typography.body2,
                    color = KnowllyTheme.colors.gray44,
                )
            }

            KnowllyContainedButton(
                text = stringResource(id = R.string.next),
                onClick = { /* TODO */ },
                modifier = Modifier
                    .padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 24.dp)
                    .fillMaxWidth(),
                enabled = false,
            )
        }
    }
}

@Preview(widthDp = 360, showBackground = true)
@Composable
fun RegisterScreenPreview() {
    KnowllyTheme {
        RegisterScreen(viewModel = viewModel())
    }
}
