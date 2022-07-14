package kr.co.knowledgerally.ui.lecture

import android.annotation.SuppressLint
import android.net.Uri
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
    domain: String,
    lectureInfoId: Long,
    navigateUp: () -> Unit
) {
    val initUrl = Uri.Builder()
        .scheme("http")
        .authority(domain)
        .appendPath("lecture-detail")
        .appendPath(lectureInfoId.toString())
        .build().toString()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        KnowllyTopAppBar(
            onNavigationClick = navigateUp
        )
        KnowllyWebView(webAppInterface = webAppInterface, initUrl = initUrl)
    }
}
