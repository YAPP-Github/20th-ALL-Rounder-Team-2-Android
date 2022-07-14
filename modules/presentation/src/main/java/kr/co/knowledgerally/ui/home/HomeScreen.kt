package kr.co.knowledgerally.ui.home

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import kr.co.knowledgerally.bridge.WebAppInterface
import kr.co.knowledgerally.ui.component.KnowllyWebView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun HomeScreen(webAppInterface: WebAppInterface) {
    KnowllyWebView(
        webAppInterface = webAppInterface,
        initUrl = "http://knowllydev-web.hkpark.net"
    )
}
