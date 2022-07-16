package kr.co.knowledgerally.bridge

import androidx.compose.runtime.Stable

@Stable
sealed interface BridgeRequest {

    object Refresh : BridgeRequest
}
