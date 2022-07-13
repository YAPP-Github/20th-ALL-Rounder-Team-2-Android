package kr.co.knowledgerally.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import kr.co.knowledgerally.bridge.WebAppInterface

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun HomeScreen(webAppInterface: WebAppInterface) {
    val context = LocalContext.current

    var url by remember { mutableStateOf("https://google.com") }
    val webViewState = rememberWebViewState(url = url)
    val navigator = rememberWebViewNavigator()

    WebView(
        state = webViewState,
        modifier = Modifier.fillMaxSize(),
        navigator = navigator,
        onCreated = {
            it.settings.javaScriptEnabled = true
            it.addJavascriptInterface(
                webAppInterface,
                "Android"
            )
        },
        client = AccompanistWebViewClient(),
        chromeClient = AccompanistWebChromeClient()
    )
}