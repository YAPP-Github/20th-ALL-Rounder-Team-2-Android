package kr.co.knowledgerally.ui.register

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun RegisterScreen(viewModel: RegisterViewModel) {
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Expanded)

    ModalBottomSheetLayout(
        sheetContent = {
            CategorySelectContent(
                sheetState = sheetState,
                onSelect = { /* TODO */ },
            )
        },
        sheetState = sheetState,
        sheetElevation = Dp.Unspecified,
        sheetBackgroundColor = KnowllyTheme.colors.grayFF,
        sheetShape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp)
    ) {
        RegisterContent()
    }
}

@Composable
fun RegisterContent() {
    // TODO
}
