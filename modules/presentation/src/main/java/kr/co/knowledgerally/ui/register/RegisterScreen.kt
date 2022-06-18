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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
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
                text = "클래스 등록하기",
                modifier = modifier.padding(top = 24.dp),
                style = KnowllyTheme.typography.headline3,
            )

            Text(
                text = "클래스 카테고리",
                modifier = modifier.padding(top = 40.dp),
                style = KnowllyTheme.typography.subtitle2,
                color = KnowllyTheme.colors.gray44,
            )
            KnowllyDropdown(
                value = "카테고리 선택",
                onClick = onCategorySelect,
                modifier = modifier.padding(top = 8.dp),
                isSelected = false,
            )

            Text(
                text = "클래스 이름",
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
                placeholder = "클래스 이름을 작성해주세요.",
            )

            Text(
                text = "클래스 소개",
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
                placeholder = "클래스 내용, 클래스 목적, 추천하는 대상 등을 포함해 클래스를 소개해보세요.",
                minHeight = 184.dp,
            )
            Row(
                modifier = modifier.padding(top = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "클래스 태그",
                    style = KnowllyTheme.typography.subtitle2,
                    color = KnowllyTheme.colors.gray44,
                )
                Text(
                    text = "(최대 5개)",
                    style = KnowllyTheme.typography.body2,
                    color = KnowllyTheme.colors.gray44,
                )
            }
            KnowllySinglelineTextField(
                value = "",
                onValueChange = { },
                modifier = modifier.padding(top = 8.dp),
                helperTextEnabled = true,
                helperText = "#’태그이름’ 입력 후 쉼표를 입력해주세요.",
                placeholder = "#태그 입력",
            )

            Row(
                modifier = modifier.padding(top = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "클래스 사진",
                    style = KnowllyTheme.typography.subtitle2,
                    color = KnowllyTheme.colors.gray44,
                )
                Text(
                    text = "(선택)",
                    style = KnowllyTheme.typography.body2,
                    color = KnowllyTheme.colors.gray44,
                )
            }

            KnowllyContainedButton(
                text = "다음",
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
