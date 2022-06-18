package kr.co.knowledgerally.ui.player

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyOutlinedButton

@Composable
fun CopyButton(
    kakaoId: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val toastMessage = stringResource(id = R.string.copied_kakao_id)
    val clipboardManager = LocalClipboardManager.current

    KnowllyOutlinedButton(
        text = stringResource(id = R.string.copy_kakao_id),
        onClick = {
            clipboardManager.setText(AnnotatedString(kakaoId))
            Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show()
        },
        modifier = modifier
    )
}