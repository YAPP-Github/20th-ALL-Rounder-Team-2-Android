package kr.co.knowledgerally.ui.lecture

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kr.co.knowledgerally.bridge.WebAppInterface
import kr.co.knowledgerally.ui.component.KnowllyTopAppBar
import kr.co.knowledgerally.ui.component.KnowllyWebView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun LectureScreen(
    webAppInterface: WebAppInterface,
    initUrl: String,
    navigateUp: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        KnowllyTopAppBar(
            onNavigationClick = navigateUp
        )
        KnowllyWebView(
            webAppInterface = webAppInterface,
            initUrl = initUrl
        )
    }
}
