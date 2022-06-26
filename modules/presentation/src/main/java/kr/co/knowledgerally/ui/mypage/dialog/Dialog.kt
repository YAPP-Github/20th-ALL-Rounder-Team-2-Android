package kr.co.knowledgerally.ui.mypage.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.component.KnowllyOutlinedButton
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun DialogContent(
    text: String,
    dismiss: String,
    onDismiss: () -> Unit,
    confirm: String,
    onConfirm: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally),
                style = KnowllyTheme.typography.subtitle3,
            )

            Row(
                modifier = Modifier
                    .padding(top = 28.dp)
                    .fillMaxWidth(),
            ) {
                KnowllyOutlinedButton(
                    text = dismiss,
                    onClick = onDismiss,
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                )
                KnowllyContainedButton(
                    text = confirm,
                    onClick = onConfirm,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp)
                        .height(40.dp)
                )
            }
        }
    }
}

@Preview(widthDp = 312)
@Composable
private fun DialogContentPreview() {
    KnowllyTheme {
        DialogContent(
            text = "로그아웃 하시겠습니까?",
            dismiss = "취소",
            onDismiss = { },
            confirm = "로그아웃",
            onConfirm = { }
        )
    }
}
