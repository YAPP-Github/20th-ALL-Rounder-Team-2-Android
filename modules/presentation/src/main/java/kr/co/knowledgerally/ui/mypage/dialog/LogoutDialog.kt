package kr.co.knowledgerally.ui.mypage.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun LogoutDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        DialogContent(
            text = stringResource(id = R.string.dialog_logout_text),
            dismiss = stringResource(R.string.dialog_logout_dismiss),
            onDismiss = onDismiss,
            confirm = stringResource(R.string.dialog_logout_confirm),
            onConfirm = onConfirm
        )
    }
}
