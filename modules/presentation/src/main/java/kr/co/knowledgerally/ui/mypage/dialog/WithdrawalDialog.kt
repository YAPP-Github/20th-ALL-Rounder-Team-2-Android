package kr.co.knowledgerally.ui.mypage.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kr.co.knowledgerally.ui.R

@Composable
fun WithdrawalDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        DialogContent(
            text = stringResource(id = R.string.dialog_withdrawal_text),
            dismiss = stringResource(R.string.dialog_withdrawal_dismiss),
            onDismiss = onDismiss,
            confirm = stringResource(R.string.dialog_withdrawal_confirm),
            onConfirm = onConfirm
        )
    }
}
