package kr.co.knowledgerally.ui.search

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
import kr.co.knowledgerally.ui.lecture.LectureActivity
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@AndroidEntryPoint
class SearchActivity : BaseWebViewActivity() {

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KnowllyTheme {
                val url by viewModel.url.collectAsState()
                val isRefresh by viewModel.isRefresh.collectAsState()
                val webViewState = rememberWebViewState(url = url)

                SearchScreen(
                    state = webViewState,
                    delegate = this
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
            val intent = LectureActivity.getIntent(this, response.lectureInfoId)
            startActivity(intent)
        }
        else -> {}
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        }
    }
}
