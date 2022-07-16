package kr.co.knowledgerally.ui.lecture

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.BaseWebViewActivity
import kr.co.knowledgerally.bridge.BridgeRequest
import kr.co.knowledgerally.bridge.BridgeResponse
import kr.co.knowledgerally.bridge.rememberWebViewState
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@AndroidEntryPoint
class LectureActivity : BaseWebViewActivity() {

    private val viewModel: LectureViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KnowllyTheme {
                val url by viewModel.url.collectAsState()
                val isRefresh by viewModel.isRefresh.collectAsState()
                val webViewState = rememberWebViewState(url = url)

                LectureScreen(
                    state = webViewState,
                    delegate = this,
                    navigateUp = ::navigateUp
                )

                LaunchedEffect(isRefresh) {
                    if (isRefresh) {
                        webViewState.request(BridgeRequest.Refresh)
                        viewModel.onRefresh()
                    }
                }
            }
        }
    }

    override fun onBridgeResponse(response: BridgeResponse) = when (response) {
        is BridgeResponse.NavigateToLecture -> {
            // TODO: URL 변경된 거로, 새로 불러오기
            viewModel.refresh()
        }
    }

    companion object {

        fun getIntent(
            context: Context,
            lectureInfoId: Long
        ): Intent = Intent(context, LectureActivity::class.java)
            .putExtra(LectureViewModel.KEY_LECTURE_INFO_ID, lectureInfoId)
    }
}
