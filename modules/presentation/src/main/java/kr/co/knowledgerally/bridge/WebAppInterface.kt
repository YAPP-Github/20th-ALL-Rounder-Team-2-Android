package kr.co.knowledgerally.bridge

import android.webkit.JavascriptInterface
import kotlinx.coroutines.runBlocking
import kr.co.knowledgerally.domain.usecase.GetJwtTokenUseCase
import kr.co.knowledgerally.toast.Toaster
import javax.inject.Inject

class WebAppInterface @Inject constructor(
    private val getJwtTokenUseCase: GetJwtTokenUseCase
) {

    @JavascriptInterface
    fun getToken(): String = runBlocking {
        getJwtTokenUseCase().getOrThrow().accessToken
    }

    @JavascriptInterface
    fun showToast(toast: String) {
        Toaster.show(toast)
    }
}