package kr.co.knowledgerally.ui.component

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import kr.co.knowledgerally.bridge.BridgeDelegate
import kr.co.knowledgerally.bridge.BridgeInterface
import kr.co.knowledgerally.bridge.BridgeRequest
import kr.co.knowledgerally.bridge.WebViewState

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun KnowllyWebView(
    url: String,
    webAppInterface: BridgeInterface,
) {
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

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun KnowllyWebView(
    state: WebViewState,
    delegate: BridgeDelegate,
) {
    var webView: WebView? by remember { mutableStateOf(null) }
    val webViewState = rememberWebViewState(state.url)
    val navigator = rememberWebViewNavigator()

    Box(modifier = Modifier.fillMaxSize()) {
        WebView(
            state = webViewState,
            modifier = Modifier.fillMaxSize(),
            navigator = navigator,
            onCreated = {
                webView = it
                it.settings.javaScriptEnabled = true
            },
            client = AccompanistWebViewClient(),
            chromeClient = AccompanistWebChromeClient()
        )

        if (webViewState.isLoading) {
            Loading()
        }
    }

    LaunchedEffect(Unit) {
        state.requestFlow.collect {
            when (it) {
                BridgeRequest.Refresh -> {
                    webView?.reload()
                }
            }
        }
    }

    DisposableEffect(webView, delegate) {
        webView?.addJavascriptInterface(BridgeInterface(delegate), BRIDGE_NAME)

        onDispose {
            webView?.removeJavascriptInterface(BRIDGE_NAME)
        }
    }
}

private const val BRIDGE_NAME = "Android"
