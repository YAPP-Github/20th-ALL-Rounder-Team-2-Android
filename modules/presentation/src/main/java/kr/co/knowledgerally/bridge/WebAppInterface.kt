package kr.co.knowledgerally.bridge

import android.content.Context
import android.webkit.JavascriptInterface
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.runBlocking
import kr.co.knowledgerally.domain.usecase.GetJwtTokenUseCase
import kr.co.knowledgerally.toast.Toaster
import kr.co.knowledgerally.ui.lecture.LectureActivity
import javax.inject.Inject

class WebAppInterface @Inject constructor(
    @ActivityContext private val context: Context,
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

    @JavascriptInterface
    fun navigateToLecture(lectureInfoId: Long) {
        val intent = LectureActivity.getIntent(
            context = context,
            lectureInfoId = lectureInfoId
        )
        context.startActivity(intent)
    }
}