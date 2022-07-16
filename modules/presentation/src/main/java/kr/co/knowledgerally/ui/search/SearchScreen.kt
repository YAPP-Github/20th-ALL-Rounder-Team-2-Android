package kr.co.knowledgerally.ui.search

import androidx.compose.runtime.Composable
import kr.co.knowledgerally.bridge.BridgeDelegate
import kr.co.knowledgerally.bridge.WebViewState
import kr.co.knowledgerally.ui.component.KnowllyWebView

@Composable
fun SearchScreen(
    state: WebViewState,
    delegate: BridgeDelegate
) {
    KnowllyWebView(
        state = state,
        delegate = delegate,
    )
}
