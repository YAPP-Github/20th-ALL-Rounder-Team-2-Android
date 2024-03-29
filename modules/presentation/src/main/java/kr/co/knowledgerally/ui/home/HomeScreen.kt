package kr.co.knowledgerally.ui.home

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import kr.co.knowledgerally.bridge.BridgeDelegate
import kr.co.knowledgerally.bridge.BridgeRequest
import kr.co.knowledgerally.bridge.BridgeResponse
import kr.co.knowledgerally.bridge.rememberWebViewState
import kr.co.knowledgerally.constant.BASE_URL
import kr.co.knowledgerally.ui.category.CategoryActivity
import kr.co.knowledgerally.ui.component.KnowllyWebView
import kr.co.knowledgerally.ui.lecture.LectureActivity
import kr.co.knowledgerally.ui.search.SearchActivity

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val isRefresh by viewModel.isRefresh.collectAsState()
    val launcher = rememberLauncherForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.refresh()
        }
    }
    val webViewState = rememberWebViewState(url = BASE_URL)
    val delegate = remember {
        BridgeDelegate { response ->
            when (response) {
                is BridgeResponse.NavigateToLecture -> {
                    val intent = LectureActivity.getIntent(context, response.lectureInfoId)
                    launcher.launch(intent)
                }
                is BridgeResponse.NavigateToCategory -> {
                    val intent = CategoryActivity.getIntent(context, response.category)
                    launcher.launch(intent)
                }
                BridgeResponse.NavigateToSearch -> {
                    SearchActivity.startActivity(context)
                }
            }
        }
    }

    KnowllyWebView(
        state = webViewState,
        delegate = delegate
    )
    LaunchedEffect(isRefresh) {
        if (isRefresh) {
            webViewState.request(BridgeRequest.Refresh)
            viewModel.onRefresh()
        }
    }
}
