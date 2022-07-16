package kr.co.knowledgerally.bridge

import android.webkit.JavascriptInterface
import kr.co.knowledgerally.toast.Toaster

class BridgeInterface(
    private val delegate: BridgeDelegate
) {

    @JavascriptInterface
    fun showToast(toast: String) {
        Toaster.show(toast)
    }

    @JavascriptInterface
    fun navigateToLecture(lectureInfoId: Long) {
        delegate.onBridgeResponse(BridgeResponse.NavigateToLecture(lectureInfoId))
    }
}
