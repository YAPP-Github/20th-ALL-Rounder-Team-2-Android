package kr.co.knowledgerally.bridge

import androidx.compose.runtime.Stable

@Stable
sealed interface BridgeResponse {

    data class NavigateToLecture(val lectureInfoId: Long) : BridgeResponse
}
