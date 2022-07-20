package kr.co.knowledgerally.bridge

import androidx.compose.runtime.Stable

@Stable
sealed interface BridgeResponse {

    data class NavigateToLecture(val lectureInfoId: Long) : BridgeResponse

    data class NavigateToCategory(val category: String) : BridgeResponse

    object NavigateToSearch : BridgeResponse

    object NavigateUp : BridgeResponse
}
