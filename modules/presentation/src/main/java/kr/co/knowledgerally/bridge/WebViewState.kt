package kr.co.knowledgerally.bridge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class WebViewState(val url: String) {

    private val _requestFlow = MutableSharedFlow<BridgeRequest>()
    val requestFlow = _requestFlow.asSharedFlow()

    suspend fun request(request: BridgeRequest) {
        _requestFlow.emit(request)
    }
}

@Composable
fun rememberWebViewState(
    url: String,
) = remember(url) { WebViewState(url) }
