package kr.co.knowledgerally.ui.player

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import kr.co.knowledgerally.toast.Toaster
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyOutlinedButton

@Composable
fun KakaoIdCopyButton(
    kakaoId: String,
    modifier: Modifier = Modifier
) {
    val clipboardManager = LocalClipboardManager.current

    KnowllyOutlinedButton(
        text = stringResource(id = R.string.copy_kakao_id),
        onClick = {
            clipboardManager.setText(AnnotatedString(kakaoId))
            Toaster.show(R.string.copied_kakao_id)
        },
        modifier = modifier
    )
}