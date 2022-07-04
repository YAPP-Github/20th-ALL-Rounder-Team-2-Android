package kr.co.knowledgerally.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.web.*
import kr.co.knowledgerally.bridge.WebAppInterface
import kr.co.knowledgerally.ui.R

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
                context.getString(R.string.javascript_interface_name)
            )
        },
        client = AccompanistWebViewClient(),
        chromeClient = AccompanistWebChromeClient()
    )
}