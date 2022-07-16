package kr.co.knowledgerally.ui.review

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
class ReviewActivity : BaseWebViewActivity() {

    private val viewModel: ReviewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KnowllyTheme {
                val url by viewModel.url.collectAsState()
                val isRefresh by viewModel.isRefresh.collectAsState()
                val webViewState = rememberWebViewState(url = url)

                ReviewScreen(
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
        BridgeResponse.NavigateUp -> {
            navigateUp()
        }
        else -> {}
    }

    companion object {

        fun getIntent(
            context: Context,
            lectureId: Long,
            coachId: Long
        ): Intent = Intent(context, ReviewActivity::class.java)
            .putExtra(ReviewViewModel.KEY_LECTURE_ID, lectureId)
            .putExtra(ReviewViewModel.KEY_COACH_ID, coachId)
    }
}
