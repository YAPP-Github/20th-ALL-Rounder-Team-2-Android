package kr.co.knowledgerally.bridge

import androidx.compose.runtime.Stable

@Stable
fun interface BridgeDelegate {

    fun onBridgeResponse(response: BridgeResponse)
}
