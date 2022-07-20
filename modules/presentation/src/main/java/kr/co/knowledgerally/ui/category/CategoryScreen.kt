package kr.co.knowledgerally.ui.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kr.co.knowledgerally.bridge.BridgeDelegate
import kr.co.knowledgerally.bridge.WebViewState
import kr.co.knowledgerally.ui.component.KnowllyTopAppBar
import kr.co.knowledgerally.ui.component.KnowllyWebView

@Composable
fun CategoryScreen(
    state: WebViewState,
    delegate: BridgeDelegate,
    navigateUp: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        KnowllyTopAppBar(onNavigationClick = navigateUp)
        KnowllyWebView(
            state = state,
            delegate = delegate,
        )
    }
}
